const express = require("express");
const knex = require("knex");
const app = express();
const cors = require("cors");
app.use(
  cors({
    origin: "http://localhost:5173",
  }),
);
app.use(express.json());
const PORT = 3000;

const database = knex({
  client: "mysql2",
  connection: {
    host: "localhost",
    user: "root",
    password: "",
    database: "stolen",
  },
});

app.get("/api/assets", async (req, res) => {
  try {
    const data = await database({ a: "assets" })
      .leftJoin({ owner: "people" }, "a.owner_id", "owner.id")
      .leftJoin({ thief: "people" }, "a.thief_id", "thief.id")
      .select(
        "a.*",
        "owner.first_name as owner_first_name",
        "owner.last_name as owner_last_name",
        "owner.birthday as owner_birthday",
        "owner.category as owner_category",
        "owner.image as owner_image",
      )
      .orderBy("a.name", "ASC");

    return res.status(200).json(data);
  } catch (err) {
    console.log(err);
    return res.status(500).json({ error: "Internal server error" });
  }
});

app.get("/api/stealings/:asset_id", async (req, res) => {
  const { asset_id } = req.params;

  try {
    const data = await database({ a: "assets" })
      .leftJoin({ owner: "people" }, "a.owner_id", "owner.id")
      .leftJoin({ thief: "people" }, "a.thief_id", "thief.id")
      .select(
        "a.*",
        "owner.first_name as owner_first_name",
        "owner.last_name as owner_last_name",
        "thief.first_name as thief_first_name",
        "thief.last_name as thief_last_name",
      )
      .where("a.id", asset_id);

    if (data.length === 0) {
      return res.status(404).json({ error: "Not found" });
    }

    return res.status(200).json(data);
  } catch (err) {
    console.log(err);
    return res.status(500).json({ error: "Internal server error" });
  }
});

app.get("/api/stolen/by-search/:search_word", async (req, res) => {
  const { search_word } = req.params;

  try {
    const data = await database({ a: "assets" })
      .leftJoin({ owner: "people" }, "a.owner_id", "owner.id")
      .leftJoin({ thief: "people" }, "a.thief_id", "thief.id")
      .select(
        "a.*",
        "owner.first_name as owner_first_name",
        "owner.last_name as owner_last_name",
        "thief.first_name as thief_first_name",
        "thief.last_name as thief_last_name",
      )
      .whereNotNull("a.thief_id")
      .andWhere((datas) => {
        datas
          .where("owner.first_name", "like", `%${search_word}%`)
          .orWhere("owner.last_name", "like", `%${search_word}%`)
          .orWhere("thief.first_name", "like", `%${search_word}%`)
          .orWhere("thief.last_name", "like", `%${search_word}%`)
          .orWhere("a.name", "like", `%${search_word}%`);
      });

    if (data.length === 0) {
      return res.status(404).json({ error: "Not found" });
    }

    return res.status(200).json(data);
  } catch (err) {
    console.log(err);
    return res.status(500).json({ error: "Internal server error" });
  }
});

app.get("/api/thieves", async (req, res) => {
  try {
    const data = await database({ a: "assets" })
      .leftJoin({ owner: "people" }, "a.owner_id", "owner.id")
      .leftJoin({ thief: "people" }, "a.thief_id", "thief.id")
      .select(
        "a.thief_id",
        "thief.first_name as first_name",
        "thief.last_name as last_name",
        "thief.image as image",
        "a.*",
      )
      .whereNotNull("a.thief_id");

    const thiefs = new Map();
    for (const row of data) {
      if (!thiefs.has(row.thief_id)) {
        thiefs.set(row.thief_id, {
          thief_id: row.thief_id,
          first_name: row.first_name,
          last_name: row.last_name,
          image: row.image,
          stolen_assets: [],
        });
      }

      thiefs.get(row.thief_id).stolen_assets.push({
        id: row.id,
        name: row.name,
        price: row.price,
        image: row.image,
        owner_id: row.owner_id,
      });
    }

    return res.status(200).json(Array.from(thiefs.values()));
  } catch (err) {
    console.log(err);
    return res.status(500).json({ error: "Internal server error" });
  }
});

app.post("/api/customer", async (req, res) => {
  const { name, price, image, owner_id, thief_id } = req.body;

  try {
    const [insertedId] = await database("assets").insert({
      name,
      price,
      image,
      owner_id,
      thief_id: thief_id ?? null,
    });

    return res.status(201).json({ id: insertedId, thief_id: thief_id ?? null });
  } catch (err) {
    console.log(err);
    return res.status(500).json({ error: "Internal server error" });
  }

  //[ asset_id, thief_id ]
});

app.listen(PORT, () => {
  console.log("Running on port: ", PORT);
});

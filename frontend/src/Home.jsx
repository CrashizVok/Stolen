import React, { useEffect, useState } from "react";
import axios from "axios";

export default function Home() {
  const [assets, setAssets] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:3000/api/assets")
      .then((response) => {
        setAssets(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  return (
    <div className="assets-container">
      {assets.map((asset) => (
        <article className="card" key={asset.id}>
          <div className="card-media">
            <img src={asset.image} alt="image" />
          </div>
          <div className="card-body">
            <div className="card-title">
              <h2>{asset.name}</h2>
            </div>
            <div className="profile">
              <div className="profilePicture">
                <img src={asset.owner_image} alt="" />
              </div>
              <div className="profileDesc">
                Owner: {asset.owner_first_name} {asset.owner_last_name}
              </div>
            </div>
          </div>
        </article>
      ))}
    </div>
  );
}

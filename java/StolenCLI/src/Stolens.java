import java.util.Objects;

public class Stolens {
    private int asset_id;
    private String asset_name;
    private int price;
    private boolean is_stolen;
    private int owner_id;
    private String owner_first_name;
    private String owner_last_name;
    private String owner_birthday;
    private int thief_id;
    private String thief_first_name;
    private String thief_last_name;
    private String thief_birthday;

    public Stolens() {}

    public void setAsset_id(int asset_id) { this.asset_id = asset_id; }
    public void setAsset_name(String asset_name) { this.asset_name = asset_name; }
    public void setPrice(int price) { this.price = price; }
    public void setIs_stolen(boolean is_stolen) { this.is_stolen = is_stolen; }
    public void setOwner_id(int owner_id) { this.owner_id = owner_id; }
    public void setOwner_first_name(String owner_first_name) { this.owner_first_name = owner_first_name; }
    public void setOwner_last_name(String owner_last_name) { this.owner_last_name = owner_last_name; }
    public void setOwner_birthday(String owner_birthday) { this.owner_birthday = owner_birthday; }
    public void setThief_id(int thief_id) { this.thief_id = thief_id; }
    public void setThief_first_name(String thief_first_name) { this.thief_first_name = thief_first_name; }
    public void setThief_last_name(String thief_last_name) { this.thief_last_name = thief_last_name; }
    public void setThief_birthday(String thief_birthday) { this.thief_birthday = thief_birthday; }

    public int getAsset_id() { return asset_id; }
    public String getAsset_name() { return asset_name; }
    public int getPrice() { return price; }
    public boolean isIs_stolen() { return is_stolen; }
    public int getOwner_id() { return owner_id; }
    public String getOwner_first_name() { return owner_first_name; }
    public String getOwner_last_name() { return owner_last_name; }
    public String getOwner_birthday() { return owner_birthday; }
    public int getThief_id() { return thief_id; }
    public String getThief_first_name() { return thief_first_name; }
    public String getThief_last_name() { return thief_last_name; }
    public String getThief_birthday() { return thief_birthday; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Stolens s = (Stolens) o;
        return asset_id == s.asset_id && price == s.price && is_stolen == s.is_stolen
                && owner_id == s.owner_id && thief_id == s.thief_id
                && Objects.equals(asset_name, s.asset_name)
                && Objects.equals(owner_first_name, s.owner_first_name)
                && Objects.equals(owner_last_name, s.owner_last_name)
                && Objects.equals(owner_birthday, s.owner_birthday)
                && Objects.equals(thief_first_name, s.thief_first_name)
                && Objects.equals(thief_last_name, s.thief_last_name)
                && Objects.equals(thief_birthday, s.thief_birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asset_id, asset_name, price, is_stolen, owner_id,
                owner_first_name, owner_last_name, owner_birthday,
                thief_id, thief_first_name, thief_last_name, thief_birthday);
    }
}
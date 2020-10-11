package beans;

public class Shop {
    private int id = 0;
    private String shopName = null;
    private String reason = null;
    private String link = null;

    public Shop(int id,String shopName,String reson,String link) {
        this.id = id;
        this.shopName = shopName;
        this.reason = reason;
        this.link = link;
    }

    public Shop(){}
    
    public void setId(int id) {
        this.id = id;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return this.id;
    }

    public String getShopName() {
        return this.shopName;
    }

    public String getReason() {
        return this.reason;
    }

    public String getLink() {
        return this.link;
    }
    
}
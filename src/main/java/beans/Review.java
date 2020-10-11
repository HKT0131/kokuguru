package beans;

public class Review {
    private int shopId = 0;
    private String opinion = null;

    public Review(int shopId,String opinion) {
        this.shopId = shopId;
        this.opinion = opinion;
    }

    public Review(){}
    
    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public int getShopId() {
        return this.shopId;
    }

    public String getOpinion() {
        return this.opinion;
    }

}
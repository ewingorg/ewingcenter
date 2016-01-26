package dto;

/**
 * 轻量级的产品信息，产品列表使用
 * 
 * @author tanson lam
 * @creation 2016年1月25日
 */
public class LightProductInfo {
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品简单描述
     */
    private String shortDesc;
    /**
     * 成本
     */
    private Float cost;
    /**
     * 价格
     */
    private Float price;
    /**
     * 图片URL
     */
    private String imageUrl;

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}

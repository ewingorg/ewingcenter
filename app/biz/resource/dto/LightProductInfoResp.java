package biz.resource.dto;

/**
 * 轻量级的产品信息，产品列表使用
 * 
 * @author tanson lam
 * @creation 2016年1月25日
 */
public class LightProductInfoResp {
    /**
     * 产品ID
     */
    private Integer id;
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
    
    private LightProductInfoReq req;

    public LightProductInfoReq getReq() {
        return req;
    }

    public void setReq(LightProductInfoReq req) {
        this.req = req;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

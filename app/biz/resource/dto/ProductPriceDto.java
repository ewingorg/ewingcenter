package biz.resource.dto;


/**
 * 产品价格
 * 
 * @author tansonlam
 * @createDate 2016年1月27日
 * 
 */
public class ProductPriceDto {
    /**
     * 
     */ 
    private Integer id;

    /**
     * 资源id，表web_resource主键
     */

    private Integer resourceId;

    /**
     * 规格ID，对应表web_resource_spec,多个以逗号分隔
     */ 
    private String specIds;

    /**
     * 产品成本价
     */
    private Float cost;

    /**
     * 产品价格
     */
    private Float price;

    /**
     * 赠送积分
     */
    private String giftScore;

    /**
     * 库存
     */
    private Integer stockNum;

    /**
     * 排序
     */
    private Integer rank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getSpecIds() {
        return specIds;
    }

    public void setSpecIds(String specIds) {
        this.specIds = specIds;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getGiftScore() {
        return giftScore;
    }

    public void setGiftScore(String giftScore) {
        this.giftScore = giftScore;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

}

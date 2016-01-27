package business.product.dto;

public class LightProductInfoReq extends PageRequest {
    /**
     * 是否热门
     */
    private Integer isHot;
    /**
     * 用户ID
     */
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

}

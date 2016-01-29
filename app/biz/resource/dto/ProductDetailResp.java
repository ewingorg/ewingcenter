package biz.resource.dto;

import java.util.List;

/**
 * 产品详情
 * 
 * @author tansonlam
 * @createDate 2016年1月26日
 * 
 */
public class ProductDetailResp {
    /**
     * 产品详情
     */
    private ProductDetailDto productDetail;
    /**
     * 产品规格
     */
    private List<ProductSpecGroup> specList;
    /**
     * 产品价格
     */
    private List<ProductPriceDto> priceList;

    public ProductDetailDto getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetailDto productDetail) {
        this.productDetail = productDetail;
    }

    public List<ProductSpecGroup> getSpecList() {
        return specList;
    }

    public void setSpecList(List<ProductSpecGroup> specList) {
        this.specList = specList;
    }

    public List<ProductPriceDto> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<ProductPriceDto> priceList) {
        this.priceList = priceList;
    }

}

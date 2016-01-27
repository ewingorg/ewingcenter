package business.product.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import business.ddl.WebResource;
import business.product.dao.WebResourceDao;
import business.product.dto.LightProductInfoReq;
import business.product.dto.LightProductInfoResp;
import business.product.dto.ProductDetailDto;
import business.product.dto.ProductDetailResp;
import business.product.dto.ProductPriceDto;
import business.product.dto.ProductSpecGroup;
import constants.IsHot;

/**
 * 资源服务类
 * 
 * @author tansonlam
 * @createDate 2016年1月26日
 * 
 */
public class WebResourceService {

    /**
     * 获取资源信息，支持分页获取数据
     * 
     * @param userId 用户ID
     * @param isHot 是否热门资源
     * @param page
     * @param pageSize
     * 
     * @return
     */
    public static List<LightProductInfoResp> pageQueryHotResource(Integer userId, IsHot isHot,
            Integer page, Integer pageSize) {

        List<LightProductInfoResp> dtoList = new ArrayList<LightProductInfoResp>();
        List<WebResource> list = WebResourceDao.pageQueryHotResource(userId, isHot, page, pageSize);
        for (WebResource webResource : list) {
            LightProductInfoResp lightProductInfo = new LightProductInfoResp();
            try {
                BeanUtils.copyProperties(lightProductInfo, webResource);
                LightProductInfoReq req = new LightProductInfoReq();
                req.setIsHot(1);
                lightProductInfo.setReq(req);
                dtoList.add(lightProductInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dtoList;
    }

    /**
     * 查找单个资源信息
     * 
     * @param resourceId
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static ProductDetailResp getProductDetail(Integer resourceId) {
        WebResource webresource = WebResourceDao.findOne(resourceId);
        if (webresource == null)
            return null;
        ProductDetailResp detailResponse = new ProductDetailResp();
        ProductDetailDto productDetail = new ProductDetailDto();
        List<ProductSpecGroup> specList = WebResourceSpecService.getConfigureSpecs(resourceId);
        List<ProductPriceDto> priceList = WebResourcePriceService.findByResourceId(resourceId);
        try {
            BeanUtils.copyProperties(productDetail, webresource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        detailResponse.setProductDetail(productDetail);
        detailResponse.setPriceList(priceList);
        detailResponse.setSpecList(specList);
        return detailResponse;
    }

}

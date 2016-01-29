package controllers.resource;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import common.constants.IsHot;
import controllers.base.BaseController;
import biz.resource.dto.LightProductInfoReq;
import biz.resource.dto.LightProductInfoResp;
import biz.resource.dto.ProductDetailReq;
import biz.resource.dto.ProductDetailResp;
import biz.resource.service.WebResourceService;

/**
 * 产品控制类，提供产品相关数据
 * 
 * @author tangzz@ucweb.com
 * @createDate 2016年1月25日
 * 
 */
public class WebResources extends BaseController {

    /**
     * 获取首页产品列表
     */
    public static void queryIndexProduct() {

        LightProductInfoReq request = getParamJson(LightProductInfoReq.class);
        Integer isHot = request.getIsHot();
        Integer page = request.getPage();
        Integer pageSize = request.getPageSize();
        Integer userId = request.getUserId();
        checkRequired(isHot, "isHot" + REQUIRED);
        checkRequired(page, "page" + REQUIRED);
        checkRequired(pageSize, "pageSize" + REQUIRED);
        checkRequired(userId, "userId" + REQUIRED);

        List<LightProductInfoResp> list = WebResourceService.pageQueryHotResource(userId,
                IsHot.fromValue(isHot), page, pageSize);
        outJsonSuccess(list);
    }

    /**
     * 查找单个资源信息
     */
    public static void getProductDetail() {
        ProductDetailReq request = getParamJson(ProductDetailReq.class);
        Integer pId = request.getpId();
        ProductDetailResp productDetailResp = WebResourceService.getProductDetail(pId);
        outJsonSuccess(productDetailResp);
    }

}

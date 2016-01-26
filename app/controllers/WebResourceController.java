package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constants.IsHot;
import business.ddl.WebResource;
import business.service.WebResourceService;
import apifw.common.RequestJson;
import dto.LightProductInfo;
import dto.LightProductInfoReq;

/**
 * 产品控制类，提供产品相关数据
 * 
 * @author tangzz@ucweb.com
 * @createDate 2016年1月25日
 * 
 */
public class WebResourceController extends BaseController {

    /**
     * 获取首页产品列表
     */
    public static void queryIndexProduct() {

        LightProductInfoReq request = getParamJson(LightProductInfoReq.class);
        Integer isHot = request.getIsHot();
        Integer page = request.getPage();
        Integer pageSize = request.getPageSize();
        List<LightProductInfo> list = WebResourceService.pageQueryHotResource(
                IsHot.fromValue(isHot), page, pageSize);
        // RequestJson requestJson = getJson();
        /*
         * List<LightProductInfo> list = new ArrayList<LightProductInfo>(); for (int i = 1; i < 5; i++) { LightProductInfo product = new
         * LightProductInfo(); product.setImageUrl("../images/haixian" + i + ".jpg"); product.setName("幸福就是可以一起睡觉");
         * product.setShortDesc("幸福就是可以一起睡觉"); product.setPrice(30f); list.add(product); }
         */
        outJsonSuccess(list);
    }
}

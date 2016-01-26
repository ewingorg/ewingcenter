package business.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import business.dao.WebResourceDao;
import business.ddl.WebResource;
import constants.IsHot;
import dto.LightProductInfo;

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
     * @param isHot 是否热门资源
     * @param page
     * @param pageSize
     * 
     * @return
     */
    public static List<LightProductInfo> pageQueryHotResource(IsHot isHot, Integer page,
            Integer pageSize) {

        List<LightProductInfo> dtoList = new ArrayList<LightProductInfo>();
        List<WebResource> list = WebResourceDao.pageQueryHotResource(isHot, page, pageSize);
        for (WebResource webResource : list) {
            LightProductInfo lightProductInfo = new LightProductInfo();
            try {
                BeanUtils.copyProperties(lightProductInfo, webResource);
                dtoList.add(lightProductInfo);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return dtoList;
    }

}

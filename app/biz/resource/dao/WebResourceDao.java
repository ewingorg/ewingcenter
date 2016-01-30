package biz.resource.dao;

import java.util.List;

import common.constants.IsEff;
import common.constants.IsHot;
import common.constants.IsOnline;

import jws.dal.sqlbuilder.Condition;
import jws.dal.sqlbuilder.Sort;
import biz.resource.ddl.WebResource;
import extend.jdbc.BaseDao;

/**
 * 用户地址相关的操作
 * 
 * @author chenxuegui.cxg@alibaba-inc.com
 * @createDate 2016年1月29日
 */
public class WebResourceDao extends BaseDao {

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
    public static List<WebResource> pageQueryHotResource(Integer userId, IsHot isHot, Integer page,
            Integer pageSize) {
        Condition condition = new Condition("WebResource.iseff", "=", IsEff.EFFECTIVE.getValue());
        condition.add(new Condition("WebResource.userId", "=", userId), "AND");
        condition.add(new Condition("WebResource.isHot", "=", isHot.getValue()), "AND");
        condition
                .add(new Condition("WebResource.isOnline", "=", IsOnline.ONLINE.getValue()), "AND");
        Sort sort = new Sort("WebResource.lastUpdate", false);
        return list(WebResource.class, condition, sort, page, pageSize);
    }

    /**
     * 查找单个资源信息
     * 
     * @param resourceId
     * @return
     */
    public static WebResource findOne(Integer resourceId) {
        return findById(resourceId, WebResource.class);
    }

}

package biz.resource.dao;

import java.util.List;

import common.constants.IsEff;

import jws.dal.sqlbuilder.Condition;
import jws.dal.sqlbuilder.Sort;
import biz.resource.ddl.WebResourcePrice;
import extend.jdbc.BaseDao;

/**
 * 资源价格
 * 
 * @author tansonlam
 * @createDate 2016年1月27日
 * 
 */
public class WebResourcePriceDao extends BaseDao {
    
    /**
     * 查找指定资源的价格设置
     * 
     * @param resourceId
     * @return
     */
    public static List<WebResourcePrice> findByResourceId(Integer resourceId) {
        Condition condition = new Condition("WebResourcePrice.iseff", "=",
                IsEff.EFFECTIVE.getValue());
        condition.add(new Condition("WebResourcePrice.resourceId", "=", resourceId), "AND");
        Sort sort = new Sort("WebResourcePrice.rank", false);
        return list(WebResourcePrice.class, condition, sort);
    }
}

package biz.resource.dao;

import java.util.List;

import common.constants.IsEff;

import jws.dal.sqlbuilder.Condition;
import jws.dal.sqlbuilder.Sort;
import biz.resource.ddl.WebResourceSpec;
import extend.jdbc.BaseDao;

public class WebResourceSpecDao extends BaseDao {

    /**
     * 获取配置的资源規格
     * 
     * @param resourceId
     * @return
     */
    public static List<WebResourceSpec> getConfigureSpecs(Integer resourceId) {
        Condition condition = new Condition("WebResourceSpec.iseff", "=",
                IsEff.EFFECTIVE.getValue());
        condition.add(new Condition("WebResourceSpec.resourceId", "=", resourceId), "AND");
        Sort sort = new Sort("WebResourceSpec.rank", false);
        return list(WebResourceSpec.class, condition, sort);
    }
}

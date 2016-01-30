package biz.customer.dao;

import java.util.List;

import jws.dal.sqlbuilder.Condition;
import jws.dal.sqlbuilder.Sort;
import biz.customer.ddl.CustomerCollect;
import biz.customer.ddl.CustomerOrder;
import extend.jdbc.BaseDao;

public class CustomerCollectDao extends BaseDao {

    public static List<CustomerCollect> findByCustomerId(Integer cusId) {
        Condition condition = new Condition("CustomerCollect.customerId", "=", cusId);
        return list(CustomerCollect.class, condition, new Sort("CustomerCollect.lastUpdate", false));
    }

}

package biz.customer.dao;

import java.util.List;

import extend.jdbc.BaseDao;
import jws.dal.sqlbuilder.Condition;
import jws.dal.sqlbuilder.Sort;
import biz.customer.ddl.CustomerOrder;

public class CustomerOrderDao extends BaseDao{

    public static List<CustomerOrder> findByCustomerId(Integer cusId, Integer state) {
        Condition condition = new Condition("CustomerOrder.customerId", "=", cusId);
        if(null == state){
            condition.add(new Condition("CustomerOrder.state", "=", state), "and");
        }
        return list(CustomerOrder.class, condition, new Sort("CustomerOrder.lastUpdate", false));
    }

}

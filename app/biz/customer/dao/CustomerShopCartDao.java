package biz.customer.dao;

import java.util.List;

import extend.jdbc.BaseDao;
import jws.dal.sqlbuilder.Condition;
import jws.dal.sqlbuilder.Sort;
import biz.customer.ddl.CustomerOrder;
import biz.customer.ddl.CustomerShopCart;

public class CustomerShopCartDao extends BaseDao{
    
    public static List<CustomerShopCart> findByCustomerId(Integer cusId) {
        Condition condition = new Condition("CustomerShopCart.customerId", "=", cusId);
        return list(CustomerShopCart.class, condition, new Sort("CustomerShopCart.lastUpdate", false));
    }

}

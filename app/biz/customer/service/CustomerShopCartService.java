package biz.customer.service;

import java.util.List;

import common.utils.IntegerUtils;
import biz.base.service.BaseService;
import biz.customer.dao.CustomerShopCartDao;
import biz.customer.ddl.CustomerShopCart;

public class CustomerShopCartService extends BaseService{

    public static List<CustomerShopCart> findByCustomerId(Integer cusId) {
        checkFalse(IntegerUtils.nullOrZero(cusId), "cursId不能为空");
        
        return CustomerShopCartDao.findByCustomerId(cusId);
    }

}

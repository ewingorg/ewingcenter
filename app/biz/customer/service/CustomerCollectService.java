package biz.customer.service;

import java.util.Collections;
import java.util.List;

import common.utils.IntegerUtils;
import biz.base.service.BaseService;
import biz.customer.dao.CustomerCollectDao;
import biz.customer.ddl.CustomerCollect;
import biz.customer.ddl.CustomerOrder;

public class CustomerCollectService extends BaseService{

    public static List<CustomerCollect> findByCustomerId(Integer cusId) {
        if(IntegerUtils.nullOrZero(cusId)){
            return Collections.EMPTY_LIST;
        }
        
        return CustomerCollectDao.findByCustomerId(cusId);
    }

}

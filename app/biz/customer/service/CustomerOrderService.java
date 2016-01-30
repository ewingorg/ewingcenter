package biz.customer.service;

import java.util.Collections;
import java.util.List;

import common.utils.IntegerUtils;
import biz.base.service.BaseService;
import biz.customer.dao.CustomerOrderDao;
import biz.customer.ddl.CustomerOrder;

/**
 * 客户订单相关的service
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月31日
 *
 */
public class CustomerOrderService extends BaseService{

    public static List<CustomerOrder> findByCustomerId(Integer cusId, Integer state) {
        if(IntegerUtils.nullOrZero(cusId)){
            return Collections.EMPTY_LIST;
        }
        
        return CustomerOrderDao.findByCustomerId(cusId, state);
    }
    
}

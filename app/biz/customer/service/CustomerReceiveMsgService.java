package biz.customer.service;

import java.util.List;

import common.utils.IntegerUtils;
import biz.base.service.BaseService;
import biz.customer.dao.CustomerReceiveMsgDao;
import biz.customer.ddl.CustomerReceiveMsg;

/**
 * 客户收获地址相关的service
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月31日
 *
 */
public class CustomerReceiveMsgService extends BaseService{
    
    /**
     * 根据客户id查找对应的收获地址
     * @param cusId
     */
    public static List<CustomerReceiveMsg> find(Integer cusId, Integer isDefault) {
        checkFalse(IntegerUtils.nullOrZero(cusId), "cusId不能为空");
        
        return CustomerReceiveMsgDao.find(cusId, isDefault);
    }
    
}

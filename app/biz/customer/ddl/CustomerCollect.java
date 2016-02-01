package biz.customer.ddl;

import static jws.dal.common.DbType.Char;
import static jws.dal.common.DbType.DateTime;
import static jws.dal.common.DbType.Int;
import jws.dal.annotation.Column;
import jws.dal.annotation.GeneratedValue;
import jws.dal.annotation.GenerationType;
import jws.dal.annotation.Id;
import jws.dal.annotation.Table;

import org.apache.commons.net.ntp.TimeStamp;

import extend.voinfo.annotation.Comment;

/**
 * 客户收藏单
 *
 * @author CodeGenerator
 * @date 2016-01-31 04:40:47
 */
@Table(name = "customer_collect")
public class CustomerCollect {
    
    /**
     * Id
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "Id")
    public Integer id;
    
    /**
     * 客户id
     */
    @Column(name = "customer_id", type = Int)
    @Comment(name = "客户id")
    public Integer customerId;
    
    /**
     * 资源id
     */
    @Column(name = "resource_id", type = Int)
    @Comment(name = "资源id")
    public Integer resourceId;
    
    /**
     * 是否生效 0:没生效 1:生效
     */
    @Column(name = "iseff", type = Char)
    @Comment(name = "是否生效 0:没生效 1:生效")
    public String iseff;
    
    /**
     * 
     */
    @Column(name = "create_time", type = DateTime)
    @Comment(name = "")
    public Long createTime;
    
    /**
     * 
     */
    @Column(name = "last_update", type = DateTime)
    @Comment(name = "")
    public Long lastUpdate;
}

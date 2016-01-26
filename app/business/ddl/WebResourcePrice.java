package business.ddl;

import static jws.dal.common.DbType.Char;
import static jws.dal.common.DbType.DateTime;
import static jws.dal.common.DbType.Float;
import static jws.dal.common.DbType.Int;
import static jws.dal.common.DbType.Varchar;
import jws.dal.annotation.Column;
import jws.dal.annotation.GeneratedValue;
import jws.dal.annotation.GenerationType;
import jws.dal.annotation.Id;
import jws.dal.annotation.Table;
import jws.dal.common.DbType;

import java.sql.Timestamp;

import core.json.annotation.Comment;

/**
 * 资源价格
 *
 * @author CodeGenerator
 * @date 2016-01-26 10:10:42
 */
@Table(name = "web_resource_price")
public class WebResourcePrice {
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "")
    public Integer id;
    
    /**
     * 资源id，表web_resource主键
     */
    @Column(name = "resource_id", type = Int)
    @Comment(name = "资源id，表web_resource主键")
    public Integer resourceId;
    
    /**
     * 规格ID，对应表web_resource_spec,多个以逗号分隔
     */
    @Column(name = "spec_ids", type = Varchar)
    @Comment(name = "规格ID，对应表web_resource_spec,多个以逗号分隔")
    public String specIds;
    
    /**
     * 产品成本价
     */
    @Column(name = "cost", type = Float)
    @Comment(name = "产品成本价")
    public Float cost;
    
    /**
     * 产品价格
     */
    @Column(name = "price", type = Float)
    @Comment(name = "产品价格")
    public Float price;
    
    /**
     * 赠送积分
     */
    @Column(name = "gift_score", type = Varchar)
    @Comment(name = "赠送积分")
    public String giftScore;
    
    /**
     * 库存
     */
    @Column(name = "stock_num", type = Int)
    @Comment(name = "库存")
    public Integer stockNum;
    
    /**
     * 排序
     */
    @Column(name = "rank", type = Int)
    @Comment(name = "排序")
    public Integer rank;
    
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

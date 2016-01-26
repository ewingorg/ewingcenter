package business.ddl;

import static jws.dal.common.DbType.Char;
import static jws.dal.common.DbType.DateTime;
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
 * 资源属性表
 *
 * @author CodeGenerator
 * @date 2016-01-26 10:10:42
 */
@Table(name = "web_resource_attr")
public class WebResourceAttr {
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "")
    public Integer id;
    
    /**
     * 属性key
     */
    @Column(name = "attr_key", type = Varchar)
    @Comment(name = "属性key")
    public String attrKey;
    
    /**
     * 属性key
     */
    @Column(name = "attr_name", type = Varchar)
    @Comment(name = "属性key")
    public String attrName;
    
    /**
     * 属性值
     */
    @Column(name = "attr_value", type = Varchar)
    @Comment(name = "属性值")
    public String attrValue;
    
    /**
     * 排序
     */
    @Column(name = "rank", type = Int)
    @Comment(name = "排序")
    public Integer rank;
    
    /**
     * 资源id，表web_resource主键
     */
    @Column(name = "resource_id", type = Int)
    @Comment(name = "资源id，表web_resource主键")
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

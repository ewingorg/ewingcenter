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
 * 模板资源的属性
 *
 * @author CodeGenerator
 * @date 2016-01-26 10:10:42
 */
@Table(name = "web_attr_conf")
public class WebAttrConf {
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "")
    public Integer id;
    
    /**
     * 模板id，web_template的主键
     */
    @Column(name = "template_id", type = Int)
    @Comment(name = "模板id，web_template的主键")
    public Integer templateId;
    
    /**
     * 属性key
     */
    @Column(name = "attr_key", type = Varchar)
    @Comment(name = "属性key")
    public String attrKey;
    
    /**
     * 属性名称
     */
    @Column(name = "attr_name", type = Varchar)
    @Comment(name = "属性名称")
    public String attrName;
    
    /**
     * 属性类型 0:text 1:textarea 2:select 3:checkbox 4:radio
     */
    @Column(name = "attr_type", type = Char)
    @Comment(name = "属性类型 0:text 1:textarea 2:select 3:checkbox 4:radio")
    public String attrType;
    
    /**
     * 对应表sys_param的系统参数编码
     */
    @Column(name = "param_code", type = Varchar)
    @Comment(name = "对应表sys_param的系统参数编码")
    public String paramCode;
    
    /**
     * 描述
     */
    @Column(name = "long_desc", type = Varchar)
    @Comment(name = "描述")
    public String longDesc;
    
    /**
     * 
     */
    @Column(name = "sort", type = Int)
    @Comment(name = "")
    public Integer sort;
    
    /**
     * 是否必填 0:不必要 1:必要
     */
    @Column(name = "require", type = Char)
    @Comment(name = "是否必填 0:不必要 1:必要")
    public String require;
    
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

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
 * 分类参数设置
 *
 * @author CodeGenerator
 * @date 2016-01-26 10:10:42
 */
@Table(name = "web_category_conf")
public class WebCategoryConf {
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "")
    public Integer id;
    
    /**
     * 分类id，表category主键
     */
    @Column(name = "category_id", type = Int)
    @Comment(name = "分类id，表category主键")
    public Integer categoryId;
    
    /**
     * 设置类型 0:参数 1:属性 2:规格
     */
    @Column(name = "type", type = Int)
    @Comment(name = "设置类型 0:参数 1:属性 2:规格")
    public Integer type;
    
    /**
     * 参数编码
     */
    @Column(name = "param_code", type = Varchar)
    @Comment(name = "参数编码")
    public String paramCode;
    
    /**
     * 参数名称
     */
    @Column(name = "param_name", type = Varchar)
    @Comment(name = "参数名称")
    public String paramName;
    
    /**
     * 参数值
     */
    @Column(name = "param_value", type = Varchar)
    @Comment(name = "参数值")
    public String paramValue;
    
    /**
     * 父编码
     */
    @Column(name = "root_code", type = Varchar)
    @Comment(name = "父编码")
    public String rootCode;
    
    /**
     * 
     */
    @Column(name = "des", type = Varchar)
    @Comment(name = "")
    public String des;
    
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

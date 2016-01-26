package business.ddl;

import static jws.dal.common.DbType.Char;
import static jws.dal.common.DbType.DateTime;
import static jws.dal.common.DbType.Int;
import static jws.dal.common.DbType.Varchar;

import java.sql.Timestamp;

import jws.dal.annotation.Column;
import jws.dal.annotation.GeneratedValue;
import jws.dal.annotation.GenerationType;
import jws.dal.annotation.Id;
import jws.dal.annotation.Table;
import jws.dal.common.DbType;
import core.json.annotation.Comment;

/**
 * 模板
 *
 * @author CodeGenerator
 * @date 2016-01-26 10:10:42
 */
@Table(name = "web_template")
public class WebTemplate {
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "")
    public Integer id;
    
    /**
     * 模板名称
     */
    @Column(name = "name", type = Varchar)
    @Comment(name = "模板名称")
    public String name;
    
    /**
     * 模板路径
     */
    @Column(name = "template_path", type = Varchar)
    @Comment(name = "模板路径")
    public String templatePath;
    
    /**
     * 
     */
    @Column(name = "group_keys", type = Varchar)
    @Comment(name = "")
    public String groupKeys;
    
    /**
     * 0:无效,1:有效
     */
    @Column(name = "iseff", type = Char)
    @Comment(name = "0:无效,1:有效")
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
    
    /**
     * 0:普通页面 1:资源模版
     */
    @Column(name = "template_type", type = Char)
    @Comment(name = "0:普通页面 1:资源模版")
    public String templateType;
}

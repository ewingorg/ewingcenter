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
 * 资源分类
 *
 * @author CodeGenerator
 * @date 2016-01-26 10:10:42
 */
@Table(name = "web_category")
public class WebCategory {
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "")
    public Integer id;
    
    /**
     * 用户ID
     */
    @Column(name = "user_id", type = Int)
    @Comment(name = "用户ID")
    public Integer userId;
    
    /**
     * 
     */
    @Column(name = "name", type = Varchar)
    @Comment(name = "")
    public String name;
    
    /**
     * 
     */
    @Column(name = "level", type = Char)
    @Comment(name = "")
    public String level;
    
    /**
     * 
     */
    @Column(name = "parentid", type = Int)
    @Comment(name = "")
    public Integer parentid;
    
    /**
     * 
     */
    @Column(name = "sort", type = Int)
    @Comment(name = "")
    public Integer sort;
    
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
}

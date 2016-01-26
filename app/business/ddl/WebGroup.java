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
 * 组配置
 *
 * @author CodeGenerator
 * @date 2016-01-26 10:10:42
 */
@Table(name = "web_group")
public class WebGroup {
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    @Comment(name = "")
    public Integer id;
    
    /**
     * 组名称
     */
    @Column(name = "name", type = Varchar)
    @Comment(name = "组名称")
    public String name;
    
    /**
     * 组key
     */
    @Column(name = "group_key", type = Varchar)
    @Comment(name = "组key")
    public String groupKey;
    
    /**
     * 链接类型 0:导航分类 1:分类链接 2:产品分类
     */
    @Column(name = "group_type", type = Char)
    @Comment(name = "链接类型 0:导航分类 1:分类链接 2:产品分类")
    public String groupType;
    
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
    
    /**
     * 
     */
    @Column(name = "type", type = Varchar)
    @Comment(name = "")
    public String type;
}

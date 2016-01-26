package job.model;

import static jws.dal.common.DbType.*;
import jws.dal.annotation.Column;
import jws.dal.annotation.GeneratedValue;
import jws.dal.annotation.GenerationType;
import jws.dal.annotation.Id;
import jws.dal.annotation.Table;

/**
 * 定时任务执行结果日志
 *
 * @author CodeGenerator
 * @date 2014-09-15 16:32:26
 */
@Table(name = "job_process_log")
public class JobProcessLog {
    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    public Integer id;
    /**
     * 创建时间
     */
    @Column(name = "create_time", type = BigInt)
    public Long createTime;

    /**
     * 执行结果
     */
    @Column(name = "message", type = Varchar)
    public String message;

    /**
     * 任务名称
     */
    @Column(name = "name", type = Varchar)
    public String name;

    /**
     * 执行结果 0:开始 1:执行成功 2:执行失败
     */
    @Column(name = "result", type = Int)
    public Integer result;

    /**
     * 更新时间
     */
    @Column(name = "update_time", type = BigInt)
    public Long updateTime;

    public Long getCreateTime() {
        return createTime;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public Integer getResult() {
        return result;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}

package job.model;

import static jws.dal.common.DbType.*;
import jws.dal.annotation.Column;
import jws.dal.annotation.GeneratedValue;
import jws.dal.annotation.GenerationType;
import jws.dal.annotation.Id;
import jws.dal.annotation.Table;

/**
 * 活动表; InnoDB free: 12288 kB
 *
 * @author CodeGenerator
 * @date 2014-09-15 16:32:26
 */
@Table(name = "job_define")
public class JobDefine {

    @Id
    @GeneratedValue(generationType = GenerationType.Auto)
    @Column(name = "id", type = Int)
    public Integer id;

    /**
     * 集群名称
     */
    @Column(name = "cluster", type = Varchar)
    public String cluster;

    /**
     * 任务名称
     */
    @Column(name = "name", type = Varchar)
    public String name;

    @Column(name = "job_cron", type = Varchar)
    public String jobCron;
    /**
     * 定义作业类名称
     */
    @Column(name = "job_class", type = Varchar)
    public String jobClass;
    /**
     * 是否生生效
     */
    @Column(name = "is_enable", type = Int)
    public Integer isEnable;

    /**
     * 创建时间
     */
    @Column(name = "create_time", type = BigInt)
    public Long createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time", type = BigInt)
    public Long updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobCron() {
        return jobCron;
    }

    public void setJobCron(String jobCron) {
        this.jobCron = jobCron;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

}

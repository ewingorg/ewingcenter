package extend.job;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import jws.Jws;
import extend.job.model.JobDefine;

/**
 * 获取配置文件里面的定时作业配置
 * 
 * @author tanson lam
 * @createDate 2015年3月7日
 * 
 */
public class JobPropDefine {
    /**
     * 获取配置文件里面的定时作业配置
     * 
     * @return
     */
    public static List<JobDefine> getPropDefineJobList() {
        List<JobDefine> defineList = new ArrayList<JobDefine>();
        Enumeration<?> enumeration = jws.Jws.configuration.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString().trim();
            if (key.startsWith("job.define.") && key.endsWith(".class")) {
                String jobName = key.trim().split("\\.")[2].trim();
                String jobClassKey = "job.define." + jobName + ".class";
                String jobCronKey = "job.define." + jobName + ".cron";
                String jobOpenKey = "job.define." + jobName + ".open"; 
                String jobNameClazzName = Jws.configuration.getProperty(jobClassKey);
                String jobCron = Jws.configuration.getProperty(jobCronKey);
                String jobOpen = Jws.configuration.getProperty(jobOpenKey,"true");
                if (jobOpen != null && jobOpen.equals("true")) {
                    JobDefine jobDefine = new JobDefine();
                    jobDefine.setName(jobName);
                    jobDefine.setJobClass(jobNameClazzName);
                    jobDefine.setJobCron(jobCron);
                    defineList.add(jobDefine);
                }
            }
        }
        return defineList;
    }
}

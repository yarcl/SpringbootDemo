package com.yarcl.springquart.quartz.example;

import com.yarcl.springquart.quartz.service.JobService;
import com.yarcl.springquart.util.JavaShellUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by xiaozhi on 2019/2/25.
 */
public class CronJob implements Job {

    @Autowired
    private JobService jobService;

    @Autowired
    @Qualifier("oracleDataSource")
    private DataSource dataSource;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        // 获取job的名称和group
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        String jobGroup = jobExecutionContext.getJobDetail().getKey().getGroup();
        // 通过Map对象获取传递的参数
        Map<String, Object> transValue = (Map<String, Object>)jobExecutionContext.getMergedJobDataMap().get("transValue");

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        // 做业务逻辑
        System.out.println(transValue.get("command").toString()+ " > " + transValue.get("logPath"));
        // int retCode = JavaShellUtil.ExecCommand();
        // 如果有这个任务，则直接删除掉
        if(null != jobKey) {
            jobService.deleteJob(jobName, jobGroup);
        }
    }
}

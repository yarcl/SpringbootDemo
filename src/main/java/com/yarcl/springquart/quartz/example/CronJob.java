package com.yarcl.springquart.quartz.example;

import com.yarcl.springquart.quartz.service.JobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xiaozhi on 2019/2/25.
 */
public class CronJob implements Job {

    @Autowired
    private JobService jobService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        // 获取job的名称和group
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        String jobGroup = jobExecutionContext.getJobDetail().getKey().getGroup();
        // 通过Map对象获取传递的参数
        Object obj = jobExecutionContext.getMergedJobDataMap().get("transValue");

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        // 做业务逻辑

        // 如果有这个任务，则直接删除掉
        if(null != jobKey) {
            jobService.deleteJob(jobName, jobGroup);
        }
    }
}

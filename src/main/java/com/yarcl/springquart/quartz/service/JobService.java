package com.yarcl.springquart.quartz.service;


import java.util.Date;
import java.util.Map;

/**
 * Created by xiaozhi on 2019/2/25.
 */
public interface JobService {
	/**
	 * 添加一个定时任务
	 * @param jobName
	 * @param jobGroup
	 * @param date
	 * @param transValue
	 * @param clazz
	 */
	void addCronJob(String jobName, String jobGroup, Date date, Map<String, Object> transValue, Class clazz);

	/**
	 * 添加异步任务
	 * @param jobName
	 * @param jobGroup
	 */
	void addAsyncJob(String jobName, String jobGroup);

	/**
	 * 暂停任务
	 * @param jobName
	 * @param jobGroup
	 */
	void pauseJob(String jobName, String jobGroup);

	/**
	 * 恢复任务
	 * @param triggerName
	 * @param triggerGroup
	 */
	void resumeJob(String triggerName, String triggerGroup);

	/**
	 * 删除job
	 * @param jobName
	 * @param jobGroup
	 */
	int deleteJob(String jobName, String jobGroup);


}
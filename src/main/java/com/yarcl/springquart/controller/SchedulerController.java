package com.yarcl.springquart.controller;

import com.yarcl.springquart.interceptor.interceptAnno.IPass;
import com.yarcl.springquart.quartz.example.CronJob;
import com.yarcl.springquart.quartz.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by xiaozhi on 2019/6/25.
 */
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    private JobService jobService;

    @Value("${spring.datasource.oracle.filters}")
    private String filters;

    @IPass
    @GetMapping("/startScheduler")
    public String startScheduler() {
        Long dateTime = new Date().getTime();
        Date date = new Date(dateTime+10000);
        System.out.print(filters);
        System.out.println("start scheduler");
        jobService.addCronJob("first", "yarcl", date, new HashMap<>(), CronJob.class);
        System.out.println("end scheduler");
        return "";
    }

}

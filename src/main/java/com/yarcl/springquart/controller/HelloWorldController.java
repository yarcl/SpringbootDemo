package com.yarcl.springquart.controller;

import com.yarcl.springquart.bean.SysUser;
import com.yarcl.springquart.beanView.Response;
import com.yarcl.springquart.interceptor.interceptAnno.IPass;
import com.yarcl.springquart.quartz.example.CronJob;
import com.yarcl.springquart.quartz.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @Autowired
    private JobService jobService;

    @GetMapping(value="/world",produces = {"application/json;charset=utf-8"})
    public String index() {
        return "Hello World";
    }

    @GetMapping("/{userId}")
    public @ResponseBody Response getAllTest(@PathVariable("userId") String userId, @RequestParam("name") String name, ModelMap map) {
        SysUser user = new SysUser();
        user.setName("Hello World:" + userId);
        return Response.success(user);
    }

    @GetMapping(value={"/","/login"})
    public String routeToWelcome(HttpServletResponse response){
        // response.sendRedirect("/login");
        return "/login";
    }

    @GetMapping(value={"/testQuartz"})
    public String routeQuartz(HttpServletResponse response){
        Date d = new Date();
        Long longTime = d.getTime()+10000;
        d = new Date(longTime);
        // response.sendRedirect("/login");
        jobService.addCronJob("hello11", "thisGroup", d, new HashMap<String, Object>(), CronJob.class);
        return "/login";
    }
}
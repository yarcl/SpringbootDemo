package com.yarcl.springquart.controller;

import com.alibaba.fastjson.JSONObject;
import com.yarcl.springquart.quartz.JobService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

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
    public @ResponseBody Object getAllTest(@PathVariable("userId") String userId, @RequestParam("name") String name, ModelMap map) {
        JSONObject obj = new JSONObject();
        obj.put("user", new String("Hello World:" + userId));
        return obj;
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
        jobService.addCronJob("hello11", "thisGroup", d);
        return "/login";
    }
}
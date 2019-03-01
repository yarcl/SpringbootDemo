package com.wbkit.bigScreen.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
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
}
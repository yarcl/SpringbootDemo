package com.wbkit.bigScreen.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class HelloWorldController {
    @RequestMapping(value="/hello", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public String index() {
        return "Hello World";
    }

    @RequestMapping("hello/{userId}")
    public @ResponseBody Object getAllTest(@PathVariable("userId") String userId, ModelMap map) {
        JSONObject obj = new JSONObject();
        obj.put("user", new String("Hello World:" + userId));
        return obj;
    }

    @RequestMapping(value={"/","/login"})
    public String routeToWelcome(HttpServletResponse response) throws IOException {
        //response.sendRedirect("/login.html");
        return "/login";
    }
}
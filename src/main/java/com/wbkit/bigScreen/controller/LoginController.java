package com.wbkit.bigScreen.controller;

import com.alibaba.fastjson.JSONObject;
import com.wbkit.bigScreen.bean.UserBean;
import com.wbkit.bigScreen.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
@ConfigurationProperties(locations = "classpath:/application.properties")
public class LoginController {
    private Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    @Value("${spring.datasource.mysql.url}")
    private String url;

    @RequestMapping(value="/login.do", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public Object routeToWelcome(String username, String password, HttpServletRequest request) throws IOException {
        JSONObject obj = new JSONObject();
        System.out.println(username);
        System.out.println(password);
        obj.put("flag", true);
        try{
            List<UserBean> users = userService.getAllUserInfo();

            if(users!=null){
                obj.put("flag", true);
            } else {
                obj.put("flag", false);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(url);
        return obj;
    }
}

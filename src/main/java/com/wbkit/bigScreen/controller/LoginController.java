package com.wbkit.bigScreen.controller;

import com.alibaba.fastjson.JSONObject;
import com.wbkit.bigScreen.bean.UserBean;
import com.wbkit.bigScreen.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
@ConfigurationProperties("application.properties")
public class LoginController {
    private Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Value("${spring.datasource.mysql.url}")
    private String url;

    @PostMapping(value="/login.do", produces = {"application/json;charset=utf-8"})
    public Object routeToWelcome(String username, String password, HttpServletRequest request) throws IOException {
        JSONObject obj = new JSONObject();
        try{
            List<UserBean> users = userService.getAllUserInfo();
            if(null !=null){
                obj.put("flag", true);
            } else {
                obj.put("flag", false);
            }
        } catch(Exception e){
            logger.error(e);
        }
        return obj;
    }
}

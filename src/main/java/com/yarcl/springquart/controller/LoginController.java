package com.yarcl.springquart.controller;

import com.alibaba.fastjson.JSONObject;
import com.yarcl.springquart.bean.RazorUser;
import com.yarcl.springquart.bean.UserBean;
import com.yarcl.springquart.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    private Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    /*@Value("${spring.datasource.mysql.url}")
    private String url;*/

    @PostMapping(value="/login1.do", produces = {"application/json;charset=utf-8"})
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

    @PostMapping("login.do")
    @ResponseBody
    public RazorUser login(String username, String password, ModelAndView mav){

        RazorUser user = userService.login(username,password);
        if(user!=null){
            user.setLoginPwd("");
        }
        return user;
    }

    @GetMapping("/loginOut.do")
    public ModelAndView loginOut(ModelAndView mav, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session!=null){
            //System.out.print(((User)session.getAttribute("user")).getName());
            session.removeAttribute("user");
        }
        mav.setViewName("/login.jsp");
        return mav;
    }
}

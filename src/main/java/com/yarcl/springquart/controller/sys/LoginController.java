package com.yarcl.springquart.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.yarcl.springquart.bean.user.User;
import com.yarcl.springquart.bean.user.UserBean;
import com.yarcl.springquart.constant.SystemConstant;
import com.yarcl.springquart.interceptor.interceptAnno.IPass;
import com.yarcl.springquart.service.UserService;
import com.yarcl.springquart.bean.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@SuppressWarnings(value = "unused")
public class LoginController {
    private Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    // 访问登录页面1
    @IPass
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

    // 访问登录页面2
    @IPass
    @ResponseBody
    @PostMapping(value = "login.do")
    public Response login(@RequestParam("username") String username, @RequestParam("password") String password){
        User user = userService.login(username,password);
        if(user!=null){
            logger.info(SystemConstant.LONGIN_SUCCESS, user.getName());
            user.setLoginPwd("");
        } else {
            logger.error(SystemConstant.LONGIN_FAILED, username);
            return Response.error();
        }
        return Response.success(user);
    }

    // 退出登录接口
    @GetMapping("/loginOut.do")
    public ModelAndView loginOut(ModelAndView mav, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session!=null){
            session.removeAttribute("user");
        }
        mav.setViewName("/login");
        return mav;
    }
}
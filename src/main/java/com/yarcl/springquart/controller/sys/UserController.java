package com.yarcl.springquart.controller.sys;

import com.yarcl.springquart.bean.user.User;
import com.yarcl.springquart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
@SuppressWarnings(value = "unused")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping(value = "/editPersonInfo.do")
    public ModelAndView editUserInfo(User user, ModelAndView mav){

        if(user!=null){
            int result = userService.updateUser(user);
            user = userService.getUserById(user.getUserId());
            mav.addObject("user", user);
        }
        mav.setViewName("/page/user/personInfo.jsp");

        return mav;
    }

    @PostMapping(value = "/editPassword.do")
    public ModelAndView editPassword(String newPwd, String userId, ModelAndView mav){
        userService.updatePassword(userId, newPwd);
        mav.setViewName("/page/user/personInfo.jsp");

        return mav;
    }

}

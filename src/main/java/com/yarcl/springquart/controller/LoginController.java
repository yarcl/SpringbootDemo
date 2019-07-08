package com.yarcl.springquart.controller;

import com.yarcl.springquart.bean.SysSession;
import com.yarcl.springquart.bean.SysUser;
import com.yarcl.springquart.beanView.Response;
import com.yarcl.springquart.constant.SystemConstant;
import com.yarcl.springquart.interceptor.interceptAnno.IPass;
import com.yarcl.springquart.service.SessionManageService;
import com.yarcl.springquart.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by yarcl on 2018/1/10.
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    private Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SessionManageService sessionManageService;

    // 访问登录页面2
    @IPass
    @ResponseBody
    @PostMapping(value = "login.do")
    public Response login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request){
        SysUser user = userService.login(username,password);

        if(!Objects.isNull(user)){
            // sessioin控制
            Boolean loginFlag = this.saveOrUpdateSession(request, user);
            if(!loginFlag) return Response.error("登录失败!");

            // 密码清空
            logger.info(SystemConstant.LONGIN_SUCCESS, user.getName());
            user.setLoginPwd("");
        } else {
            logger.error(SystemConstant.LONGIN_FAILED, username);
            return Response.error("用户不存在!");
        }
        return Response.success(user);
    }

    // 退出登录接口
    @GetMapping("/loginOut.do")
    public ModelAndView loginOut(ModelAndView mav, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session!=null){
            //System.out.print(((User)session.getAttribute("user")).getName());
            session.removeAttribute("user");
        }
        mav.setViewName("/login");
        return mav;
    }

    // session Control
    private Boolean saveOrUpdateSession(HttpServletRequest request, SysUser user) {
        HttpSession session = Optional.ofNullable(request.getSession()).orElse(null);

        if(!Objects.isNull(session)) {
            SysSession sysSession = new SysSession();
            StringBuffer ipAddress = request.getRequestURL();

            sysSession.setSessionId(session.getId());
            sysSession.setIpAddress(ipAddress.toString());
            sysSession.setUserId(user.getUserId());
            sysSession.setUserName(user.getName());
            SysSession resultSession = sessionManageService.getSysSessionById(session.getId());
            int sum = resultSession != null ? 100 : sessionManageService.saveSessionInfo(sysSession);
            return true;
        } else {
            return false;
        }
    }
}

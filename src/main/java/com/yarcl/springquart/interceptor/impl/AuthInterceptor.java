package com.yarcl.springquart.interceptor.impl;

import com.yarcl.springquart.bean.SysSession;
import com.yarcl.springquart.interceptor.interceptAnno.IPass;
import com.yarcl.springquart.service.SessionManageService;
import com.yarcl.springquart.service.impl.SessionManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.util.Objects;

/**
 * Created by xiaozhi on 2019/6/26.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private SessionManageService sessionManageService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getSession().getId();
        Boolean loginState;
        // IPass info
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IPass methodAnnotation = handlerMethod.getMethodAnnotation(IPass.class);
        loginState = (methodAnnotation != null && methodAnnotation.value().equals(IPass.Auth.PASS));

        SysSession sysSession = sessionManageService.getSysSessionById(sessionId);
        return !Objects.isNull(sysSession)?sysSession!=null:loginState;
    }
}

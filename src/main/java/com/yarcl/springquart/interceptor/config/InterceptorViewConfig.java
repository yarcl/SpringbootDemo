package com.yarcl.springquart.interceptor.config;

import com.yarcl.springquart.interceptor.impl.AuthInterceptor;
import com.yarcl.springquart.interceptor.impl.RouterInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhi on 2019/6/26.
 */
@Configuration
public class InterceptorViewConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns(this.fetchAuthIncp());
        // registry.addInterceptor(new RouterInterceptor()).addPathPatterns(this.fetchAuthIncp());
    }

    /**
     * 权限过滤内容
     * @return
     */
    private List<String> fetchAuthIncp() {
        List<String> filterList = new ArrayList<>();
        filterList.add("/");
        filterList.add("/**.do");
        filterList.add("/**.servlet");
        filterList.add("/*");
        filterList.add("/*/*");
        return filterList;
    }

}
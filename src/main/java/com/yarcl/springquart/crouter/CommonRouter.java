package com.yarcl.springquart.crouter;

import com.yarcl.springquart.interceptor.interceptAnno.IPass;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 路由接口
 * Created by Administrator on 2018/1/10.
 */
@RestController
@RequestMapping(value="/router")
public class CommonRouter {

    // 路由到登录页面
    @IPass
    @GetMapping(value={"/", "login.html"}, produces = {"application/json;charset=utf-8"})
    public ModelAndView routToLogin() throws IOException {
        ModelAndView  mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    // main路径下登录成功路由到首页
    @GetMapping(value="/main/{main}.html", produces = {"application/json;charset=utf-8"})
    public ModelAndView routeToIndex(@PathVariable("main") String main) throws IOException {
        ModelAndView  mav = new ModelAndView();
        mav.setViewName("main/"+main);
        return mav;
    }

    /*// 路由首页上方内容
    @GetMapping(value="/header", produces = {"application/json;charset=utf-8"})
    public ModelAndView routeHeader() throws IOException {
        ModelAndView  mav = new ModelAndView();
        mav.setViewName("main/header");
        return mav;
    }

    // 路由到左菜单栏
    @GetMapping(value="/left", produces = {"application/json;charset=utf-8"})
    public ModelAndView routeLeft() throws IOException {
        ModelAndView  mav = new ModelAndView();
        mav.setViewName("main/left");
        return mav;
    }*/

    // page目录下的路由
    @GetMapping(value="/{path}/{pageView}.html", produces = {"application/json;charset=utf-8"})
    public ModelAndView routePersonInfo(@PathVariable("path") String path, @PathVariable("pageView") String pageView)  {
        ModelAndView  mav = new ModelAndView();
        mav.setViewName("page/"+path+"/"+pageView);
        return mav;
    }

    /*// 路由到左菜单栏
    @GetMapping(value="/{path}/{pageView}")
    public ModelAndView routeSystemInfo(@PathVariable("path") String path, @PathVariable("pageView") String pageView)  {
        ModelAndView  mav = new ModelAndView();
        mav.setViewName("page/"+path+"/"+pageView);
        return mav;
    }*/

}

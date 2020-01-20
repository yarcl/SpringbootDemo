package com.yarcl.springquart.crouter;

import com.yarcl.springquart.interceptor.interceptAnno.IPass;
import com.yarcl.springquart.interceptor.interceptAnno.RouterPath;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 路由接口,页面路由访问接口层
 * Created by Administrator on 2018/1/10.
 */
@RestController
@RequestMapping(value="/router")
@RouterPath(value = RouterPath.Router.ROUTER_PATH)
@SuppressWarnings(value = "unused")
public class CommonRouter {
    /**
     * login路由
     * @return
     */
    @IPass
    @GetMapping(value={"/", "login.html"}, produces = {"application/json;charset=utf-8"})
    public ModelAndView routToLogin() {
        ModelAndView  mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    /**
     * main路由-->main路径下登录成功路由到首页
     * @param main
     * @return
     */
    @GetMapping(value="/main/{main}.html", produces = {"application/json;charset=utf-8"})
    public ModelAndView routeToIndex(@PathVariable("main") String main) {
        ModelAndView  mav = new ModelAndView();
        mav.setViewName("main/"+main);
        return mav;
    }

    /**
     * page路由-->page目录下的路由
     * @param path
     * @param pageView
     * @return
     */
    @IPass
    @GetMapping(value="/{path}/{pageView}.html", produces = {"application/json;charset=utf-8"})
    public ModelAndView routePagInfo(@PathVariable("path") String path, @PathVariable("pageView") String pageView)  {
        ModelAndView  mav = new ModelAndView();
        mav.setViewName("page/"+path+"/"+pageView);
        return mav;
    }
}

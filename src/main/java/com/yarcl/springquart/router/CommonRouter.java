package com.yarcl.springquart.router;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Administrator on 2018/1/10.
 */
@RestController
@RequestMapping(value="/router")
public class CommonRouter {

    @GetMapping(value={"/", "login"}, produces = {"application/json;charset=utf-8"})
    public ModelAndView routToLogin() throws IOException {
        ModelAndView  mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping(value="/index", produces = {"application/json;charset=utf-8"})
    public ModelAndView routeToIndex() throws IOException {
        ModelAndView  mav = new ModelAndView();
        mav.setViewName("main/index");
        return mav;
    }

}

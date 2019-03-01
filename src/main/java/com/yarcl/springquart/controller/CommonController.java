package com.yarcl.springquart.controller;

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
@RequestMapping(value="/comm")
public class CommonController {


    @GetMapping(value="/index", produces = {"application/json;charset=utf-8"})
    public ModelAndView routeToIndex() throws IOException {
        ModelAndView  mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

}

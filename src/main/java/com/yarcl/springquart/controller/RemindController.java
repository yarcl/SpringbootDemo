package com.yarcl.springquart.controller;

import com.yarcl.springquart.bean.beanView.Response;
import com.yarcl.springquart.bean.remind.RemindAddQo;
import com.yarcl.springquart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Shares on 2020/1/20.
 */
@RestController
@RequestMapping(name = "/remind")
public class RemindController {

    @Autowired
    private ProductService productService;

    @GetMapping("remindList.do")
    public ModelAndView remindInfo(ModelAndView mav){
        System.out.println("hello remind!!!");
        mav.setViewName("/page/remind/remindList.html");
        return mav;
    }

    @PostMapping(value = "addRemind.do", produces = {"application/json;charset=utf-8"})
    public Response remindAdd(@RequestBody RemindAddQo remindAddQo) {
        System.out.println("新增提醒内容");
        return Response.success();
    }

}

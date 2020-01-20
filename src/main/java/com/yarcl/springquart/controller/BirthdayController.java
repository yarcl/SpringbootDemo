package com.yarcl.springquart.controller;

import com.yarcl.springquart.bean.beanView.Response;
import com.yarcl.springquart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/birthday")
@SuppressWarnings(value = "unused")
public class BirthdayController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/birthList.do")
    public Response<ModelAndView> allProductInfo(ModelAndView mav, String userId, String nowPage){
        System.out.println("hello product!!!");
        mav.setViewName("/page/birthday/birthList.html");
        return Response.error(mav);
    }

}

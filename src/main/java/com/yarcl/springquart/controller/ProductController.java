package com.yarcl.springquart.controller;

import com.yarcl.springquart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/product")
@SuppressWarnings(value = "unused")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/myProductInfo.do")
    public ModelAndView allProductInfo(ModelAndView mav, String userId, String nowPage){
        mav.setViewName("/page/product/productList.html");
        return mav;
    }

}

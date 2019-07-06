package com.yarcl.springquart.controller;

import com.yarcl.springquart.bean.Birthday;
import com.yarcl.springquart.bean.PageBean;
import com.yarcl.springquart.beanView.Response;
import com.yarcl.springquart.interceptor.interceptAnno.IPass;
import com.yarcl.springquart.service.BirthdayService;
import com.yarcl.springquart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/birthday")
public class BirthdayController {

    @Autowired
    private BirthdayService birthdayService;

    @RequestMapping("/birthList.do")
    public Response<Birthday> allProductInfo(PageBean pageBean){
        List<Birthday> birthdayList = birthdayService.getAllBirthdayInfo();
        return Response.success(birthdayList);
    }
}

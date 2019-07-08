package com.yarcl.springquart.controller;

import com.yarcl.springquart.bean.Birthday;
import com.yarcl.springquart.bean.util.PageBean;
import com.yarcl.springquart.beanView.Response;
import com.yarcl.springquart.service.BirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/birthday")
public class BirthdayController {

    @Autowired
    private BirthdayService birthdayService;

    @ResponseBody
    @PostMapping("/birthList.do")
    public Response<Birthday> allProductInfo(@RequestParam("nowPage") int nowPage,
                                             @RequestParam("pageSize") int pageSize){

        List<Birthday> birthdayList = birthdayService.queryBirthdayInfoByPage(nowPage, pageSize);
        int count = birthdayService.queryCount();
        PageBean<Birthday> pageVos = new PageBean<>(nowPage, pageSize, count, birthdayList);
        return Response.success(pageVos);
    }
}

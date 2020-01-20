package com.yarcl.springquart.controller;

import com.yarcl.springquart.bean.remind.RemindAddQo;
import com.yarcl.springquart.beanView.Response;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shares on 2020/1/20.
 */
@RestController
@RequestMapping(name = "/remind")
@Api(tags = "提醒模块", description = "需要提醒的内容")
public class RemindController {

    @PostMapping(value = "/addRemind.do")
    public Response remindAdd(@RequestBody RemindAddQo remindAddQo) {
        System.out.println("新增提醒内容");

        return Response.success();
    }

}

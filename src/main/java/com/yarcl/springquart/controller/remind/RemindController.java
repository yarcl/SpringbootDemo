package com.yarcl.springquart.controller.remind;

import com.yarcl.springquart.bean.Response;
import com.yarcl.springquart.bean.remind.RemindAddQo;
import com.yarcl.springquart.bean.remind.RemindVo;
import com.yarcl.springquart.service.remind.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by Shares on 2020/1/20.
 */
@RestController
@RequestMapping(value = "/remind")
@SuppressWarnings(value = "unused")
public class RemindController {

    @Autowired
    private RemindService remindService;

    @GetMapping("/remindList.do")
    public Response remindInfo(ModelAndView mav){
        List<RemindVo> remindVoList = remindService.queryRemindList();
        RemindVo remindVo = RemindVo.builder().remindCount("1").remindDate(new Date())
                .remindFre("2").remindTitle("11").remindType("2").createDate(new Date()).isDelete("0").pushPath("11").pushType("222").build();
        remindVoList.add(remindVo);
        return Response.success(remindVoList);
    }

    @PostMapping(value = "addRemind.do")
    public Response remindAdd(@RequestBody RemindAddQo remindAddQo) {
        System.out.println("新增提醒内容");
        return Response.success();
    }

}

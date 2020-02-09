package com.yarcl.springquart.controller.remind;

import com.yarcl.springquart.bean.Response;
import com.yarcl.springquart.bean.remind.RemindAddQo;
import com.yarcl.springquart.bean.remind.RemindDo;
import com.yarcl.springquart.bean.remind.RemindVo;
import com.yarcl.springquart.service.remind.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    public Response remindInfo(){
        List<RemindVo> remindVoList = remindService.queryRemindList();
        return Response.success(remindVoList);
    }

    @GetMapping("/deleteRemind.do")
    public Response deleteRemind(@RequestParam("id") String remindId) {
        if(StringUtils.isEmpty(remindId))
            return Response.error("参数传递失败");
        int num = remindService.deleteRemind(remindId);
        return Response.success(num);
    }

    @ResponseBody
    @PostMapping(value = "addRemind.do", produces = {"application/json;charset=utf-8"})
    public Response remindAdd(@RequestBody RemindAddQo remindAddQo) {
        RemindVo remindVo = remindService.save(remindAddQo);
        return Response.success(remindVo);
    }

}

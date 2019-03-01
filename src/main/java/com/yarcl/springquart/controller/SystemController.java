package com.yarcl.springquart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/system")
public class SystemController {

    @RequestMapping("/systemInfo.do")
    public ModelAndView catchSystemInfo(ModelAndView mav){
        return mav;
    }

}

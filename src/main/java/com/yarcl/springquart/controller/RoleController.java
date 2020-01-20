package com.yarcl.springquart.controller;

import com.yarcl.springquart.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/allRoleInfo.do")
    public ModelAndView allRoleInfo(ModelAndView mav, String nowPage){
        return mav;
    }

}

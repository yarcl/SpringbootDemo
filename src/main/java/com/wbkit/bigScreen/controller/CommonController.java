package com.wbkit.bigScreen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * Created by Administrator on 2018/1/10.
 */
@Controller
@RequestMapping(value="/comm")
public class CommonController {


    @RequestMapping(value="/index", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public String routeToIndex() throws IOException {
        return "main/index";
    }

}

package com.yarcl.springquart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shares on 2020/1/20.
 */
@RestController
@RequestMapping("/")
@SuppressWarnings(value = "unused")
public class ErrorController {

    @GetMapping("error.do")
    public String allMenuInfo(){
        return "hello error";
    }
}

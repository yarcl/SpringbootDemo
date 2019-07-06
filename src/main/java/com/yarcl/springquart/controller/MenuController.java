package com.yarcl.springquart.controller;

import com.yarcl.springquart.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @GetMapping("/allMenuInfo.do")
    public ModelAndView allMenuInfo(ModelAndView mav, String nowPage){
        /*if(nowPage!=null && !"".equals(nowPage)){
            pageBean.setNowPage(Integer.parseInt(nowPage));
        }
        List<SysMenu> menuList = menuService.allMenuInfo(pageBean);
        pageBean.setCount(menuService.getMenuCount());
        mav.addObject("page", pageBean);
        mav.addObject("menuList", menuList);
        mav.setViewName("/page/menu/allMenuInfo.jsp");*/

        return mav;
    }

}

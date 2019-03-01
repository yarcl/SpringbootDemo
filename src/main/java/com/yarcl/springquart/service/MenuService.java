package com.yarcl.springquart.service;


import com.yarcl.springquart.bean.PageBean;
import com.yarcl.springquart.bean.RazorMenu;

import java.util.List;

public interface MenuService {
    public List<RazorMenu> allMenuInfo(PageBean pageBean);

    public int getMenuCount();
}

package com.yarcl.springquart.service;


import com.yarcl.springquart.bean.PageBean;
import com.yarcl.springquart.bean.SysMenu;

import java.util.List;

public interface MenuService {
    public List<SysMenu> allMenuInfo(PageBean pageBean);

    public int getMenuCount();
}

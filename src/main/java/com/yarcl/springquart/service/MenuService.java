package com.yarcl.springquart.service;


import com.yarcl.springquart.bean.PageBean;
import com.yarcl.springquart.bean.menu.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> allMenuInfo(PageBean pageBean);

    public int getMenuCount();
}

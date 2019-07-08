package com.yarcl.springquart.service.impl;


import com.yarcl.springquart.bean.util.PageBean;
import com.yarcl.springquart.bean.SysMenu;
import com.yarcl.springquart.dao.MenuMapper;
import com.yarcl.springquart.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /*public List<SysMenu> allMenuInfo(PageBean pageBean) {
        return menuMapper.allMenuInfo(CaculatePage.caculateStart(pageBean), CaculatePage.caculateEnd(pageBean));
    }*/

    @Override
    public List<SysMenu> allMenuInfo(PageBean pageBean) {
        return null;
    }

    public int getMenuCount() {
        return menuMapper.getMenuCount();
    }
}

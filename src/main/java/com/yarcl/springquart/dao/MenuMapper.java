package com.yarcl.springquart.dao;

import com.yarcl.springquart.bean.SysMenu;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface MenuMapper {

    public List<SysMenu> allMenuInfo(int startPage, int endPage);

    public int getMenuCount();

}

package com.yarcl.springquart.dao;

import com.yarcl.springquart.bean.RazorMenu;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface MenuMapper {

    List<RazorMenu> allMenuInfo(int startPage, int endPage);

    int getMenuCount();

}

package com.yarcl.springquart.dao;

import com.yarcl.springquart.bean.menu.Menu;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface MenuMapper {

    List<Menu> allMenuInfo(int startPage, int endPage);

    int getMenuCount();

}

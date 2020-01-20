package com.yarcl.springquart.dao;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface RoleMapper {
    // List<RazorRole> allRoleInfo(int startPage, int endPage);

    int getRoleCount();
}

package com.yarcl.springquart.dao;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface RoleMapper {
    // public List<SysRole> allRoleInfo(int startPage, int endPage);

    public int getRoleCount();
}

package com.yarcl.springquart.service.impl;


import com.yarcl.springquart.dao.RoleMapper;
import com.yarcl.springquart.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /*public List<SysRole> allRoleInfo(PageBean pageBean) {
        return roleMapper.allRoleInfo(CaculatePage.caculateStart(pageBean), CaculatePage.caculateEnd(pageBean));
    }*/

    public int getRoleCount() {
        return roleMapper.getRoleCount();
    }
}

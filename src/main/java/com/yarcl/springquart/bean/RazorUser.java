package com.yarcl.springquart.bean;

import lombok.Data;

@Data
public class RazorUser {

    private int userId;
    private String loginName;
    private String loginPwd;
    private String name;
    private String roleId;
    private String isActive;

    public RazorUser() {

    }

    public RazorUser(int userId, String loginName, String loginPwd, String name, String roleId, String isActive) {
        this.userId = userId;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.name = name;
        this.roleId = roleId;
        this.isActive = isActive;
    }
}

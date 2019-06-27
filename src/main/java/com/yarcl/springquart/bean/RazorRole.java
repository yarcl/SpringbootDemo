package com.yarcl.springquart.bean;

import lombok.Data;

@Data
public class RazorRole {
    private int roleId;
    private String roleName;
    private String isActive;
    private String isDelete;

    public RazorRole() {
    }

    public RazorRole(int roleId, String roleName, String isActive, String isDelete) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.isActive = isActive;
        this.isDelete = isDelete;
    }

}

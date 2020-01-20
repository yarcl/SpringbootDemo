package com.yarcl.springquart.bean.role;

import lombok.Data;

@Data
public class Role {
    private int roleId;
    private String roleName;
    private String isActive;
    private String isDelete;

    public Role() {
    }

    public Role(int roleId, String roleName, String isActive, String isDelete) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.isActive = isActive;
        this.isDelete = isDelete;
    }

}

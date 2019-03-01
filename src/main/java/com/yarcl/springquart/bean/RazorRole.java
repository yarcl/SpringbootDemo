package com.yarcl.springquart.bean;

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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}

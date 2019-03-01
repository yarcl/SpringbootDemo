package com.yarcl.springquart.bean;

public class RazorMenu {
    private int menuId;
    private String menuName;
    private String menuUrl;
    private String isActive;
    private String isDelete;
    private String parentId;

    public RazorMenu() {
    }

    public RazorMenu(int menuId, String menuName, String menuUrl, String isActive, String isDelete, String parentId) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.isActive = isActive;
        this.isDelete = isDelete;
        this.parentId = parentId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}

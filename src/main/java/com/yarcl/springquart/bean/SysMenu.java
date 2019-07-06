package com.yarcl.springquart.bean;

import lombok.Data;

@Data
public class SysMenu {
    private int menuId;
    private String menuName;
    private String menuUrl;
    private String isActive;
    private String isDelete;
    private String parentId;

    public SysMenu() {
    }

    public SysMenu(int menuId, String menuName, String menuUrl, String isActive, String isDelete, String parentId) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.isActive = isActive;
        this.isDelete = isDelete;
        this.parentId = parentId;
    }
}

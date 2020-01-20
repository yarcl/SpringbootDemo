package com.yarcl.springquart.bean.user;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/12.
 */
@Data
public class UserBean implements Serializable {
    private String id;
    private String username;
    private String password;
    private String age;

    public UserBean() {
    }

    public UserBean(String id, String username, String password, String age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }
}

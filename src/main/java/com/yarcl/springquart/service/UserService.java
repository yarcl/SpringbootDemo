package com.yarcl.springquart.service;

import com.yarcl.springquart.bean.RazorUser;
import com.yarcl.springquart.bean.UserBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public interface UserService {

    List<UserBean> getAllUserInfo();

    RazorUser login(String username, String password);

    int updateUser(RazorUser user);

    RazorUser getUserById(int id);

    int updatePassword(String userId, String newPwd);

    // public List<RazorUser> getAllUsersInfo(PageBean pageBean);

    int getUserCount();
}

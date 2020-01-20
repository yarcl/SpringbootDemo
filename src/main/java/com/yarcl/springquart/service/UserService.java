package com.yarcl.springquart.service;

import com.yarcl.springquart.bean.user.User;
import com.yarcl.springquart.bean.user.UserBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public interface UserService {

    List<UserBean> getAllUserInfo();

    User login(String username, String password);

    int updateUser(User user);

    User getUserById(int id);

    int updatePassword(String userId, String newPwd);

    // public List<RazorUser> getAllUsersInfo(PageBean pageBean);

    int getUserCount();
}

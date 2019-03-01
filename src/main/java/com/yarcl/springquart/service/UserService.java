package com.yarcl.springquart.service;

import com.yarcl.springquart.bean.RazorUser;
import com.yarcl.springquart.bean.UserBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public interface UserService {

    List<UserBean> getAllUserInfo();

    public RazorUser login(String username, String password);

    public int updateUser(RazorUser user);

    public RazorUser getUserById(int id);

    public int updatePassword(String userId, String newPwd);

    // public List<RazorUser> getAllUsersInfo(PageBean pageBean);

    public int getUserCount();
}

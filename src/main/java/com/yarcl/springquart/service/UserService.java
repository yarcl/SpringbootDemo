package com.yarcl.springquart.service;

import com.yarcl.springquart.bean.SysUser;
import com.yarcl.springquart.bean.UserBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public interface UserService {

    List<UserBean> getAllUserInfo();

    public SysUser login(String username, String password);

    public int updateUser(SysUser user);

    public SysUser getUserById(int id);

    public int updatePassword(String userId, String newPwd);

    // public List<SysUser> getAllUsersInfo(PageBean pageBean);

    public int getUserCount();
}

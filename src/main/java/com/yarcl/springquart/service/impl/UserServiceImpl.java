package com.yarcl.springquart.service.impl;

import com.yarcl.springquart.bean.RazorUser;
import com.yarcl.springquart.bean.UserBean;
import com.yarcl.springquart.dao.mysql.UserMapper;
import com.yarcl.springquart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Override
    public List<UserBean> getAllUserInfo() {
        return userMapper.findUserInfo();
    }

    public RazorUser login(String username, String password) {
        return userMapper.login(username, password);
    }

    /*public List<RazorUser> getAllUsersInfo(PageBean pageBean) {
        return userMapper.getAllUsersInfo(CaculatePage.caculateStart(pageBean), CaculatePage.caculateEnd(pageBean));
    }*/

    public int updateUser(RazorUser user) {
        return userMapper.updateUser(user);
    }

    public RazorUser getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public int getUserCount() {
        return userMapper.getUserCount();
    }

    public int updatePassword(String userId, String newPwd) {
        return userMapper.updatePassword(userId, newPwd);
    }
}

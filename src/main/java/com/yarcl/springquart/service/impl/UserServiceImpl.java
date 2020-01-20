package com.yarcl.springquart.service.impl;

import com.yarcl.springquart.bean.user.User;
import com.yarcl.springquart.bean.user.UserBean;
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

    public User login(String username, String password) {
        return userMapper.login(username, password);
    }

    /*public List<RazorUser> getAllUsersInfo(PageBean pageBean) {
        return userMapper.getAllUsersInfo(CaculatePage.caculateStart(pageBean), CaculatePage.caculateEnd(pageBean));
    }*/

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public int getUserCount() {
        return userMapper.getUserCount();
    }

    public int updatePassword(String userId, String newPwd) {
        return userMapper.updatePassword(userId, newPwd);
    }
}

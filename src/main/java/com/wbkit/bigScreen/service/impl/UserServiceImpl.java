package com.wbkit.bigScreen.service.impl;

import com.wbkit.bigScreen.bean.UserBean;
import com.wbkit.bigScreen.dao.mysql.UserMapper;
import com.wbkit.bigScreen.dao.oracle.TestUserMapper;
import com.wbkit.bigScreen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    public UserMapper userMapper;

    @Override
    public List<UserBean> getAllUserInfo() {
        return userMapper.findUserInfo();
    }
}

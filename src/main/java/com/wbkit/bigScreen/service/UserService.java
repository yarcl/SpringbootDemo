package com.wbkit.bigScreen.service;

import com.wbkit.bigScreen.bean.UserBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public interface UserService {

    List<UserBean> getAllUserInfo();
}

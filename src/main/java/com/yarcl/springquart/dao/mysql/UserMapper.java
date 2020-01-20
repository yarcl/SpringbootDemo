package com.yarcl.springquart.dao.mysql;

import com.yarcl.springquart.bean.user.User;
import com.yarcl.springquart.bean.user.UserBean;
import org.mybatis.spring.annotation.MapperScan;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */
@MapperScan
public interface UserMapper {
    List<UserBean> findUserInfo();

    User login(String username, String password);

    int updateUser(User user);

    User getUserById(int id);

    int updatePassword(String userId, String newPwd);

    List<User> getAllUsersInfo(int startPage, int endPage);

    int getUserCount();
}

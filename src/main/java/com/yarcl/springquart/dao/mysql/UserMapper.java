package com.yarcl.springquart.dao.mysql;

import com.yarcl.springquart.bean.RazorUser;
import com.yarcl.springquart.bean.UserBean;
import org.mybatis.spring.annotation.MapperScan;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */
@MapperScan
public interface UserMapper {
    List<UserBean> findUserInfo();

    RazorUser login(String username, String password);

    int updateUser(RazorUser user);

    RazorUser getUserById(int id);

    int updatePassword(String userId, String newPwd);

    List<RazorUser> getAllUsersInfo(int startPage, int endPage);

    int getUserCount();
}

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

    public RazorUser login(String username, String password);

    public int updateUser(RazorUser user);

    public RazorUser getUserById(int id);

    public int updatePassword(String userId, String newPwd);

    public List<RazorUser> getAllUsersInfo(int startPage, int endPage);

    public int getUserCount();
}

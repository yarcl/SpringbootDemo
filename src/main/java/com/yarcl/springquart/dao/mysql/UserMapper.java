package com.yarcl.springquart.dao.mysql;

import com.yarcl.springquart.bean.SysUser;
import org.mybatis.spring.annotation.MapperScan;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */
@MapperScan
public interface UserMapper {

    SysUser login(String username, String password);

    int updateUser(SysUser user);

    SysUser getUserById(int id);

    int updatePassword(String userId, String newPwd);

    List<SysUser> getAllUsersInfo(int startPage, int endPage);

    int getUserCount();
}

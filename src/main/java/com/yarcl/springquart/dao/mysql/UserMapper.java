package com.yarcl.springquart.dao.mysql;

import com.yarcl.springquart.bean.SysUser;
import org.mybatis.spring.annotation.MapperScan;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */
@MapperScan
public interface UserMapper {

    public SysUser login(String username, String password);

    public int updateUser(SysUser user);

    public SysUser getUserById(int id);

    public int updatePassword(String userId, String newPwd);

    public List<SysUser> getAllUsersInfo(int startPage, int endPage);

    public int getUserCount();
}

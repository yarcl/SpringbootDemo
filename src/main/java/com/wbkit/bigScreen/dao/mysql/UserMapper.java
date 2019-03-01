package com.wbkit.bigScreen.dao.mysql;

import com.wbkit.bigScreen.bean.UserBean;
import org.mybatis.spring.annotation.MapperScan;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */
@MapperScan
public interface UserMapper {
    List<UserBean> findUserInfo();
}

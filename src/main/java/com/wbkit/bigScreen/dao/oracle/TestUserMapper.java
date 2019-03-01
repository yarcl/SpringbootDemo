package com.wbkit.bigScreen.dao.oracle;

import com.wbkit.bigScreen.bean.UserBean;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */
@MapperScan
public interface TestUserMapper {
    List<UserBean> findUserInfo();
}

package com.yarcl.springquart.dao;

import com.yarcl.springquart.bean.SysSession;
import org.mybatis.spring.annotation.MapperScan;

/**
 * Created by xiaozhi on 2019/7/6.
 */
@MapperScan
public interface SessionMapper {


    int saveSession(SysSession sysSession);

    SysSession getSysSessionById(String sessionId);
}

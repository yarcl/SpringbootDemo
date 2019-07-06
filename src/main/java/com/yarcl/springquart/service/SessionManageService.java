package com.yarcl.springquart.service;

import com.yarcl.springquart.bean.SysSession;

/**
 * Created by xiaozhi on 2019/7/6.
 */
public interface SessionManageService {

    int saveSessionInfo(SysSession sysSession);

    SysSession getSysSessionById(String sessionId);
}

package com.yarcl.springquart.service.impl;

import com.yarcl.springquart.bean.SysSession;
import com.yarcl.springquart.dao.SessionMapper;
import com.yarcl.springquart.service.SessionManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaozhi on 2019/7/6.
 */
@Service
public class SessionManageServiceImpl implements SessionManageService {

    @Autowired
    SessionMapper sessionMapper;

    @Override
    public int saveSessionInfo(SysSession sysSession) {
        return sessionMapper.saveSession(sysSession);
    }

    @Override
    public SysSession getSysSessionById(String sessionId) {
        return sessionMapper.getSysSessionById(sessionId);
    }
}

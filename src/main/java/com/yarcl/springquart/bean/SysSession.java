package com.yarcl.springquart.bean;

import lombok.Data;

/**
 * Created by xiaozhi on 2019/7/6.
 */
@Data
public class SysSession {

    int id;

    String sessionId;

    int userId;

    String userName;

    String ipAddress;

}

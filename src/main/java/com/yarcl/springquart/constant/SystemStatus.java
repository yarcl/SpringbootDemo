package com.yarcl.springquart.constant;

/**
 * Created by xiaozhi on 2019/3/9.
 */
public enum SystemStatus {

    LONGIN_FAILED(0,"{0}登录失败！"),
    LONGIN_SUCCESS(1, "{0}登录成功！");

    private int code;
    private String desc;

    SystemStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    int getCode(){
        return code;
    }
    String getDesc(){
        return desc;
    }

}

package com.yarcl.springquart.vbean;

import com.alibaba.fastjson.JSONObject;

public class Response {

    private Object content;

    private int flag;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public static Response error(){
        Response response = new Response();
        response.setFlag(0);
        response.setContent(null);
        return response;
    }

    public static Response error(Object content){
        Response response = new Response();
        response.setFlag(0);
        response.setContent(null);
        return response;
    }

    public static Response success(){
        Response response = new Response();
        response.setFlag(1);
        response.setContent(null);
        return response;
    }

    public static Response success(Object content){
        Response response = new Response();
        response.setFlag(1);
        response.setContent(content);
        return response;
    }
}

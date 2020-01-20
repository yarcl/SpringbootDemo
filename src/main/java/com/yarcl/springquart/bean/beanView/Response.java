package com.yarcl.springquart.bean.beanView;

import lombok.Data;

@Data
public class Response<T> {

    /**
     * 返回的对象内容
     */
    private T content;

    private Object message;

    private Boolean success;

    public static Response error(){
        Response response = new Response();
        response.setSuccess(false);
        response.setContent(null);
        return response;
    }

    public static <T> Response error(T content){
        Response response = new Response();
        response.setSuccess(false);
        response.setContent(content);
        return response;
    }

    public static Response success(){
        Response response = new Response();
        response.setSuccess(true);
        response.setContent(null);
        return response;
    }

    public static <T> Response success(T content){
        Response response = new Response();
        response.setSuccess(true);
        response.setContent(content);
        return response;
    }
}

package com.example.shopping.mall.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String retCode;
    private T data;

    private ServerResponse(String retCode) {
        this.retCode = retCode;
    }

    private ServerResponse(String retCode, T data) {
        this.retCode = retCode;
        this.data = data;
    }

    public String getRetCode() {
        return retCode;
    }

    public T getData() {
        return data;
    }

    /**
     * 以下為回傳建構子
     */
    public static <T> ServerResponse<T> create(String retCode) {
        return new ServerResponse<T>(retCode);
    }

    public static <T> ServerResponse<T> create(String retCode, T msg) {
        return new ServerResponse<T>(retCode, msg);
    }
}

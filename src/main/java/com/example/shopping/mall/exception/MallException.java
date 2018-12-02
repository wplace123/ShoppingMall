package com.example.shopping.mall.exception;

/**
 * 
 * @author ryan wang
 * @date 2018/11/09
 */
public class MallException extends Exception {

    private static final long serialVersionUID = 1L;
    private String code;

    public String getCode() {
        return code;
    }

    public MallException(String code, String message) {
        super(message);
        this.code = code;
    }
}

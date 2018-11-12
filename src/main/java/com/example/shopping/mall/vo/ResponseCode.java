package com.example.shopping.mall.vo;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
public enum ResponseCode {
    /**
     * succes code
     */
    SUCCES_CODE("200"),
    /**
     * error code
     */
    ERROR_CODE("400");

    private final String retCode;

    ResponseCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetCode() {
        return retCode;
    }
}

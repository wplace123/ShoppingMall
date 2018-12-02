package com.example.shopping.mall.exception.info;

/**
 * 
 * @author ryan wang
 * @date 2018/11/09
 */
public enum MallExceptionInfoEnum {

    /**
     * id
     */
    ARGS_ERR1("001", "參數錯誤"),

    XXX_ERR2("002", "xx");

    private MallExceptionInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * error code
     */
    private String code;
    /**
     * error message
     */
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

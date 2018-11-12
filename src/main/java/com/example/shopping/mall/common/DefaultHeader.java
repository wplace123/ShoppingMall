package com.example.shopping.mall.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
public class DefaultHeader {

    protected static final HttpHeaders HEADERS = new HttpHeaders();
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    static {
        HEADERS.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }
}

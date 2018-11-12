package com.example.shopping.mall.controller;

import static com.example.shopping.mall.vo.ResponseCode.ERROR_CODE;
import static com.example.shopping.mall.vo.ResponseCode.SUCCES_CODE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.mall.common.DefaultHeader;
import com.example.shopping.mall.exception.MallException;
import com.example.shopping.mall.service.imp.TestServiceImpl;
import com.example.shopping.mall.vo.ServerResponse;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
@RestController
public class Test extends DefaultHeader {

    @Autowired
    TestServiceImpl testService;

    @RequestMapping(value = "/test")
    public ResponseEntity<ServerResponse<String>> test1() {
        logger.info("test");
        try {

            return new ResponseEntity<ServerResponse<String>>(
                ServerResponse.create(SUCCES_CODE.getRetCode(), testService.test()), DefaultHeader.HEADERS,
                HttpStatus.OK);
        } catch (Exception e) {

            if (e instanceof MallException) {
                // 相對業務邏輯
                return new ResponseEntity<ServerResponse<String>>(ServerResponse.create(SUCCES_CODE.getRetCode(), "成功"),
                    DefaultHeader.HEADERS, HttpStatus.OK);

            }
            // 特殊紀錄
            return new ResponseEntity<ServerResponse<String>>(
                ServerResponse.create(ERROR_CODE.getRetCode(), e.getMessage()), DefaultHeader.HEADERS, HttpStatus.OK);
        }

    }
}

// {"retCode":"suss","data":"aaa"}

// {"retCode":"err","errMsg":"異常"}
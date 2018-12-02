package com.example.shopping.mall.service;

import com.example.shopping.mall.service.dto.TestReq;
import com.example.shopping.mall.service.dto.TestResp;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
public interface ITestService {
    /**
     * test method
     * 
     * @param req
     * @return
     */
    public TestResp test01(TestReq req);

}

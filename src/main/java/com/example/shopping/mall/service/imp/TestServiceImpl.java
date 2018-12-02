package com.example.shopping.mall.service.imp;

import org.springframework.stereotype.Service;

import com.example.shopping.mall.exception.MallException;
import com.example.shopping.mall.exception.info.MallExceptionInfoEnum;
import com.example.shopping.mall.service.ITestService;
import com.example.shopping.mall.service.dto.TestReq;
import com.example.shopping.mall.service.dto.TestResp;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
@Service
public class TestServiceImpl implements ITestService {
    public String test() throws MallException {

        String a = "a";
        String b = "b";

        if (!a.equals(b)) {
            throw new MallException(MallExceptionInfoEnum.XXX_ERR2.getCode(),
                MallExceptionInfoEnum.XXX_ERR2.getMessage());

        }
        return "ok";

    }

    @Override
    public TestResp test01(TestReq req) {
        return null;
    }
}

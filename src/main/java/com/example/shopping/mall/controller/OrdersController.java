package com.example.shopping.mall.controller;

import static com.example.shopping.mall.vo.ResponseCode.SUCCES_CODE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.mall.common.DefaultHeader;
import com.example.shopping.mall.model.OrdersModel;
import com.example.shopping.mall.service.imp.OrdersServiceImpl;
import com.example.shopping.mall.vo.ServerResponse;

/**
 * 
 * @author ryan wang
 * @date 2018/11/11
 */
@RestController
public class OrdersController extends DefaultHeader {
    @Autowired
    OrdersServiceImpl ordersService;

    @RequestMapping(value = "/get/order/all")
    public ResponseEntity<ServerResponse<List<OrdersModel>>> getAll() {
        ResponseEntity<ServerResponse<List<OrdersModel>>> rerspEntity =
            new ResponseEntity<ServerResponse<List<OrdersModel>>>(
                ServerResponse.create(SUCCES_CODE.getRetCode(), ordersService.getAll()), DefaultHeader.HEADERS,
                HttpStatus.OK);
        return rerspEntity;
    }

    @RequestMapping(value = "/get/order/status/{iStatus}")
    public ResponseEntity<ServerResponse<List<OrdersModel>>> findByStatus(@PathVariable int iStatus) {
        ResponseEntity<ServerResponse<List<OrdersModel>>> rerspEntity =
            new ResponseEntity<ServerResponse<List<OrdersModel>>>(
                ServerResponse.create(SUCCES_CODE.getRetCode(), ordersService.findByStatus(iStatus)),
                DefaultHeader.HEADERS, HttpStatus.OK);
        return rerspEntity;
    }
}

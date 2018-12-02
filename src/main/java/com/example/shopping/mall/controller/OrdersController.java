package com.example.shopping.mall.controller;

import static com.example.shopping.mall.vo.ResponseCode.SUCCES_CODE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.mall.common.DefaultHeader;
import com.example.shopping.mall.exception.MallException;
import com.example.shopping.mall.model.OrdersModel;
import com.example.shopping.mall.service.IOrdersService;
import com.example.shopping.mall.vo.ResponseCode;
import com.example.shopping.mall.vo.ServerResponse;

/**
 * 
 * @author ryan wang
 * @date 2018/11/11
 */
@RestController
@RequestMapping(value = "/api")
public class OrdersController extends DefaultHeader {
    @Autowired
    IOrdersService ordersService;

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> getAll() {
        return new ResponseEntity<ServerResponse>(
            ServerResponse.create(SUCCES_CODE.getRetCode(), ordersService.getAll()), DefaultHeader.HEADERS,
            HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> findByStatus(@PathVariable int id) {
        return new ResponseEntity<ServerResponse>(
            ServerResponse.create(SUCCES_CODE.getRetCode(), ordersService.findById(id)), DefaultHeader.HEADERS,
            HttpStatus.OK);
    }

    /**
     * 動態查詢
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> getOrders(@RequestBody OrdersModel ordersModel) {
        return new ResponseEntity<ServerResponse>(
            ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), ordersService.findAll(ordersModel)),
            DefaultHeader.HEADERS, HttpStatus.OK);
    }

    /**
     * update
     * 
     * @return
     */

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/orders/update", method = RequestMethod.PUT)
    public ResponseEntity<ServerResponse> updateOrders(@RequestBody OrdersModel ordersModel) {
        try {
            return new ResponseEntity<ServerResponse>(
                ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), ordersService.update(ordersModel)),
                DefaultHeader.HEADERS, HttpStatus.OK);
        } catch (MallException e) {
            return new ResponseEntity<ServerResponse>(
                ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), e.getMessage()), DefaultHeader.HEADERS,
                HttpStatus.OK);
        }

    }

    /**
     * create
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/orders/create", method = RequestMethod.PUT)
    public ResponseEntity<ServerResponse> createOrders(@RequestBody OrdersModel ordersModel) {
        if (ordersModel.getId() == null) {
            return new ResponseEntity<ServerResponse>(
                ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), ordersService.save(ordersModel)),
                DefaultHeader.HEADERS, HttpStatus.OK);
        } else {
            return new ResponseEntity<ServerResponse>(
                ServerResponse.create(ResponseCode.ERROR_CODE.getRetCode(), "Id must be null"), DefaultHeader.HEADERS,
                HttpStatus.OK);
        }
    }

    /**
     * delete
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ServerResponse> delete(@PathVariable int id) {
        String result = "";
        try {
            result = ordersService.delete(id);
        } catch (MallException e) {
            return new ResponseEntity<ServerResponse>(
                ServerResponse.create(ResponseCode.ERROR_CODE.getRetCode(), e.getMessage()), DefaultHeader.HEADERS,
                HttpStatus.OK);
        }

        if (id < 1) {
            return new ResponseEntity<ServerResponse>(ServerResponse.create(ResponseCode.ERROR_CODE.getRetCode(), ""),
                DefaultHeader.HEADERS, HttpStatus.OK);
        }

        return new ResponseEntity<ServerResponse>(ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), result),
            DefaultHeader.HEADERS, HttpStatus.OK);
    }
}

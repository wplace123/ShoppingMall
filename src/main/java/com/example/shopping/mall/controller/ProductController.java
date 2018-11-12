package com.example.shopping.mall.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.mall.common.DefaultHeader;
import com.example.shopping.mall.model.ProductModel;
import com.example.shopping.mall.service.imp.ProductServiceImpl;
import com.example.shopping.mall.vo.ResponseCode;
import com.example.shopping.mall.vo.ServerResponse;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */

// 獲取所有分類 ok
// 獲取所有商品 / 分頁
// 獲取單個商品細節 ok
// 多條件查詢商品

@RestController
public class ProductController extends DefaultHeader {
    @Autowired
    ProductServiceImpl service;

    @RequestMapping(value = "/get/product/all")
    public ResponseEntity<ServerResponse<List<ProductModel>>> getAll() {
        ResponseEntity<ServerResponse<List<ProductModel>>> entity =
            new ResponseEntity<ServerResponse<List<ProductModel>>>(
                ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), service.getAll()), DefaultHeader.HEADERS,
                HttpStatus.OK);
        return entity;
    }

    @RequestMapping(value = "/get/product/{id}")
    public ResponseEntity<ServerResponse<Optional<ProductModel>>> getAll(@PathVariable int id) {
        ResponseEntity<ServerResponse<Optional<ProductModel>>> entity =
            new ResponseEntity<ServerResponse<Optional<ProductModel>>>(
                ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), service.findById(id)),
                DefaultHeader.HEADERS, HttpStatus.OK);
        return entity;
    }

}

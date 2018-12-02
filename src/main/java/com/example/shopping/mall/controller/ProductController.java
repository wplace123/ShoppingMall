package com.example.shopping.mall.controller;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.mall.common.DefaultHeader;
import com.example.shopping.mall.exception.MallException;
import com.example.shopping.mall.exception.info.MallExceptionInfoEnum;
import com.example.shopping.mall.model.ProductModel;
import com.example.shopping.mall.service.IProductService;
import com.example.shopping.mall.vo.ResponseCode;
import com.example.shopping.mall.vo.ServerResponse;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */

// 獲取所有分類 ok
// 獲取所有商品 / 分頁 ok
// 獲取單個商品細節 ok
// 多條件查詢商品 ok

@RestController
@RequestMapping(value = "/api")
public class ProductController extends DefaultHeader {

    @Autowired
    IProductService productService;

    /**
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> getAll() {
        return new ResponseEntity<ServerResponse>(
            ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), productService.getAll().getList()),
            DefaultHeader.HEADERS, HttpStatus.OK);
    }

    /**
     * TODO 驗證size失敗
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> getOneProductById(@PathVariable @Validated @Size(min = 1, max = 2) int id) {
        System.out.println("***** id : " + id);
        return new ResponseEntity<ServerResponse>(
            ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), productService.findById(id)),
            DefaultHeader.HEADERS, HttpStatus.OK);
    }

    /**
     * 動態查詢
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> getProduct(@RequestBody ProductModel productModel) {
        return new ResponseEntity<ServerResponse>(
            ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), productService.findAll(productModel)),
            DefaultHeader.HEADERS, HttpStatus.OK);
    }

    /**
     * 修改
     * 
     * @return
     */

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/products/update", method = RequestMethod.PUT)
    public ResponseEntity<ServerResponse> updateProduct(@RequestBody ProductModel productModel) {
        try {
            return new ResponseEntity<ServerResponse>(
                ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), productService.update(productModel)),
                DefaultHeader.HEADERS, HttpStatus.OK);
        } catch (MallException e) {
            return new ResponseEntity<ServerResponse>(
                ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), e.getMessage()), DefaultHeader.HEADERS,
                HttpStatus.OK);
        }

    }

    /**
     * 創建
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/products/create", method = RequestMethod.PUT)
    public ResponseEntity<ServerResponse> createProduct(@RequestBody ProductModel productModel) {
        if (productModel.getId() == null) {
            return new ResponseEntity<ServerResponse>(
                ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), productService.save(productModel)),
                DefaultHeader.HEADERS, HttpStatus.OK);
        } else {
            return new ResponseEntity<ServerResponse>(
                ServerResponse.create(ResponseCode.ERROR_CODE.getRetCode(), "Id must be null"), DefaultHeader.HEADERS,
                HttpStatus.OK);
        }
    }

    /**
     * 
     * @return
     */

    //
    /**
     * TODO 補上錯誤訊息， 已id參數為例 controller 各自都要做防呆嗎? controller 錯誤判斷 補上訊息? ResponseCode 參數錯誤
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ServerResponse> delete(@PathVariable int id) {
        String result = "";
        try {
            result = productService.delete(id);
        } catch (MallException e) {
            return new ResponseEntity<ServerResponse>(
                ServerResponse.create(ResponseCode.ERROR_CODE.getRetCode(), e.getMessage()), DefaultHeader.HEADERS,
                HttpStatus.OK);
        }

        if (id < 1) {
            return new ResponseEntity<ServerResponse>(
                ServerResponse.create(ResponseCode.ERROR_CODE.getRetCode(), MallExceptionInfoEnum.ARGS_ERR1),
                DefaultHeader.HEADERS, HttpStatus.OK);
        }

        return new ResponseEntity<ServerResponse>(ServerResponse.create(ResponseCode.SUCCES_CODE.getRetCode(), result),
            DefaultHeader.HEADERS, HttpStatus.OK);
    }

}

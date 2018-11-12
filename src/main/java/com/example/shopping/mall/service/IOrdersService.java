package com.example.shopping.mall.service;

import java.util.List;

import com.example.shopping.mall.model.OrdersModel;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
public interface IOrdersService {
    /**
     * get all order data
     * 
     * @return
     */
    public List<OrdersModel> getAll();

    /**
     * find data by status
     * 
     * @param status
     * @return
     */
    public List<OrdersModel> findByStatus(int status);
}

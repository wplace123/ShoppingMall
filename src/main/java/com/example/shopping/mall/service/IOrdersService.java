package com.example.shopping.mall.service;

import java.util.List;
import java.util.Optional;

import com.example.shopping.mall.exception.MallException;
import com.example.shopping.mall.model.OrdersModel;
import com.github.pagehelper.PageInfo;

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
    public PageInfo<OrdersModel> getAll();

    /**
     * 動態查詢
     * 
     * @param ordersModel
     * @return
     */
    List<OrdersModel> findAll(OrdersModel ordersModel);

    /**
     * save
     * 
     * @param ordersModel
     * @return
     */
    public OrdersModel save(OrdersModel ordersModel);

    /**
     * update
     * 
     * @param ordersModel
     * @return
     * @throws MallException
     */
    public OrdersModel update(OrdersModel ordersModel) throws MallException;

    /**
     * find by id
     * 
     * @param id
     * @return
     */
    public Optional<OrdersModel> findById(int id);

    /**
     * delete
     * 
     * @param id
     * @return
     * @throws MallException
     */
    public String delete(int id) throws MallException;
}

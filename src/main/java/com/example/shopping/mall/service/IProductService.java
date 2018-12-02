package com.example.shopping.mall.service;

import java.util.List;
import java.util.Optional;

import com.example.shopping.mall.exception.MallException;
import com.example.shopping.mall.model.ProductModel;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
public interface IProductService {
    /**
     * 獲取所有分類
     * 
     * @return
     */
    public PageInfo<ProductModel> getAll();

    /**
     * 動態查詢
     * 
     * @param productModel
     * @return
     */
    List<ProductModel> findAll(ProductModel productModel);

    /**
     * find by id
     * 
     * @param id
     * @return
     */
    public Optional<ProductModel> findById(int id);

    /**
     * save
     * 
     * @param productModel
     * @return
     */
    public ProductModel save(ProductModel productModel);

    /**
     * update
     * 
     * @param productModel
     * @return
     * @throws MallException
     */
    public ProductModel update(ProductModel productModel) throws MallException;

    /**
     * delete
     * 
     * @param id
     * @return
     * @throws MallException
     */
    public String delete(int id) throws MallException;
}

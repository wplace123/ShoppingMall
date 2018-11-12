package com.example.shopping.mall.service;

import java.util.List;
import java.util.Optional;

import com.example.shopping.mall.model.ProductModel;

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
    public List<ProductModel> getAll();

    /**
     * find by id
     * 
     * @param id
     * @return
     */
    public Optional<ProductModel> findById(int id);
}

package com.example.shopping.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.mall.model.ProductDetailModel;

/**
 * 
 * @author ryan wang
 * @date 2018/11/09
 */
public interface ProductDetailRepository extends JpaRepository<ProductDetailModel, Integer> {

}

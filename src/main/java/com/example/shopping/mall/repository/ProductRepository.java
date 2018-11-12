package com.example.shopping.mall.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.mall.model.ProductModel;

/**
 * 
 * @author ryan wang
 * @date 2018/11/09
 */
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    /**
     * find by id
     * 
     * @param id
     * @return
     */
    Optional<ProductModel> findById(Integer id);
}

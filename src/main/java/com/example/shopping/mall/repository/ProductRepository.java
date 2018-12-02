package com.example.shopping.mall.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
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

    /**
     * 動態查詢
     * 
     * @param specification
     * @return
     */
    List<ProductModel> findAll(Specification<ProductModel> specification);
}

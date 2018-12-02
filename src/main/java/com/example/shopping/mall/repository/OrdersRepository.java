package com.example.shopping.mall.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.mall.model.OrdersModel;

/**
 * 
 * @author ryan wang
 * @date 2018/11/09
 */
public interface OrdersRepository extends JpaRepository<OrdersModel, Integer> {
    /**
     * find by id
     * 
     * @param id
     * @return
     */
    Optional<OrdersModel> findById(Integer id);

    /**
     * 動態查詢
     * 
     * @param specification
     * @return
     */
    List<OrdersModel> findAll(Specification<OrdersModel> specification);
}

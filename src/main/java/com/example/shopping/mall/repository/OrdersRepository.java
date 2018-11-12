package com.example.shopping.mall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.mall.model.OrdersModel;

/**
 * 
 * @author ryan wang
 * @date 2018/11/09
 */
public interface OrdersRepository extends JpaRepository<OrdersModel, Integer> {
    /**
     * find date by status
     * 
     * @param status
     * @return
     */
    List<OrdersModel> findByStatus(Integer status);
}

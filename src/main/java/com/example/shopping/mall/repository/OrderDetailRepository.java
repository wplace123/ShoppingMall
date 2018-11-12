package com.example.shopping.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.mall.model.OrderDetailModel;

/**
 * 
 * @author ryan wang
 * @date 2018/11/09
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetailModel, Integer> {

}

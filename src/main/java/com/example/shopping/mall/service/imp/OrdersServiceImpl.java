package com.example.shopping.mall.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.mall.model.OrdersModel;
import com.example.shopping.mall.repository.OrdersRepository;
import com.example.shopping.mall.service.IOrdersService;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private OrdersRepository repository;

    @Override
    public List<OrdersModel> getAll() {
        return repository.findAll();
    }

    @Override
    public List<OrdersModel> findByStatus(int status) {
        return repository.findByStatus(status);
    }

}

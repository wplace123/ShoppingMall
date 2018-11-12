package com.example.shopping.mall.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.mall.model.ProductModel;
import com.example.shopping.mall.repository.ProductRepository;
import com.example.shopping.mall.service.IProductService;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<ProductModel> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ProductModel> findById(int id) {
        return repository.findById(id);
    }

}

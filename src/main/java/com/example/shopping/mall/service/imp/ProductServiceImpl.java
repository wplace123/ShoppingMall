package com.example.shopping.mall.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.shopping.mall.common.Utils;
import com.example.shopping.mall.exception.MallException;
import com.example.shopping.mall.exception.info.MallExceptionInfoEnum;
import com.example.shopping.mall.model.ProductModel;
import com.example.shopping.mall.repository.ProductRepository;
import com.example.shopping.mall.service.IProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
@Service
public class ProductServiceImpl implements IProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public PageInfo<ProductModel> getAll() {
        PageHelper.startPage(0, 1);
        PageInfo<ProductModel> info = new PageInfo<>(productRepository.findAll());
        return info;
    }

    @Override
    public Optional<ProductModel> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductModel> findAll(ProductModel productModel) {
        return productRepository.findAll(new Specification<ProductModel>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<ProductModel> root, CriteriaQuery<?> query,
                CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (Utils.isPositiveInteger(productModel.getId())) {
                    predicates.add(criteriaBuilder.equal(root.get("id"), productModel.getId()));
                }

                if (StringUtils.isNotBlank(productModel.getType())) {
                    predicates.add(criteriaBuilder.equal(root.get("type"), productModel.getType()));
                }

                if (Utils.isPositiveInteger(productModel.getClassId())) {
                    predicates.add(criteriaBuilder.equal(root.get("classId"), productModel.getClassId()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }

        });
    }

    @Override
    public ProductModel save(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    @Override
    public ProductModel update(ProductModel productModel) throws MallException {
        if (productModel.getId() == null || (findById(productModel.getId()).orElse(null) == null)) {
            throw new MallException(MallExceptionInfoEnum.ARGS_ERR1.getCode(),
                MallExceptionInfoEnum.ARGS_ERR1.getMessage());
        }
        return productRepository.save(productModel);
    }

    /**
     * TODO findById null 判斷
     */
    @Override
    public String delete(int id) throws MallException {
        Optional<ProductModel> productModel = findById(id);
        if (productModel.orElse(null) == null) {
            throw new MallException(MallExceptionInfoEnum.ARGS_ERR1.getCode(),
                MallExceptionInfoEnum.ARGS_ERR1.getMessage());
        }
        productRepository.delete(productModel.get());
        return "Success";
    }

}

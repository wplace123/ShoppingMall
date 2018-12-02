package com.example.shopping.mall.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.shopping.mall.common.Utils;
import com.example.shopping.mall.exception.MallException;
import com.example.shopping.mall.exception.info.MallExceptionInfoEnum;
import com.example.shopping.mall.model.OrdersModel;
import com.example.shopping.mall.repository.OrdersRepository;
import com.example.shopping.mall.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public PageInfo<OrdersModel> getAll() {
        // return repository.findAll();
        PageHelper.startPage(0, 1);
        PageInfo<OrdersModel> info = new PageInfo<>(ordersRepository.findAll());
        return info;
    }

    @Override
    public Optional<OrdersModel> findById(int id) {
        return ordersRepository.findById(id);
    }

    @Override
    public List<OrdersModel> findAll(OrdersModel ordersModel) {
        return ordersRepository.findAll(new Specification<OrdersModel>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<OrdersModel> root, CriteriaQuery<?> query,
                CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (Utils.isPositiveInteger(ordersModel.getId())) {
                    predicates.add(criteriaBuilder.equal(root.get("id"), ordersModel.getId()));
                }

                if (Utils.isPositiveInteger(ordersModel.getStatus())) {
                    predicates.add(criteriaBuilder.equal(root.get("status"), ordersModel.getStatus()));
                }

                if (Utils.isPositiveInteger(ordersModel.getAccountId())) {
                    predicates.add(criteriaBuilder.equal(root.get("accountId"), ordersModel.getAccountId()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }

        });
    }

    @Override
    public OrdersModel update(OrdersModel ordersModel) throws MallException {
        if (ordersModel.getId() == null || (findById(ordersModel.getId()).orElse(null) == null)) {
            throw new MallException(MallExceptionInfoEnum.ARGS_ERR1.getCode(),
                MallExceptionInfoEnum.ARGS_ERR1.getMessage());
        }
        return ordersRepository.save(ordersModel);
    }

    @Override
    public OrdersModel save(OrdersModel ordersModel) {
        return ordersRepository.save(ordersModel);
    }

    @Override
    public String delete(int id) throws MallException {
        Optional<OrdersModel> ordersModel = findById(id);
        if (ordersModel.orElse(null) == null) {
            throw new MallException(MallExceptionInfoEnum.ARGS_ERR1.getCode(),
                MallExceptionInfoEnum.ARGS_ERR1.getMessage());
        }
        ordersRepository.delete(ordersModel.get());
        return "Success";
    }

}

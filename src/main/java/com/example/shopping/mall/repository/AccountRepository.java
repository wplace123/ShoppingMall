package com.example.shopping.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.mall.model.AccountModel;

/**
 * 
 * @author ryan wang
 * @date 2018/11/09
 */
public interface AccountRepository extends JpaRepository<AccountModel, Integer> {

    ///
    // ...
    //
    // .....

    ///
    // jpa => spring data => (jpa ,hine,link , orm , .....redis,)

}

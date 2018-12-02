package com.example.shopping.mall.service.imp;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.shopping.mall.model.AccountModel;
import com.example.shopping.mall.model.JwtTokenUser;
import com.example.shopping.mall.repository.AccountRepository;
import com.example.shopping.mall.util.TokenUtils;

/**
 * 
 * @author ryan wang
 * @date 2018/12/02
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * TO UNDERSTANDING return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.err.println("in loadUserByUsername method");
        AccountModel accountModel = new AccountModel();
        accountModel.setUserName(username);
        Example<AccountModel> exAccount = Example.of(accountModel);
        AccountModel findAccount = accountRepository.findOne(exAccount).get();
        // createToken
        String token = TokenUtils.createToken(findAccount);
        // redis
        String json = findAccount.getJsonString(findAccount);
        stringRedisTemplate.opsForValue().set(token, json, 1000, TimeUnit.SECONDS);

        System.err.println("out loadUserByUsername method");
        return new JwtTokenUser(findAccount, token);
    }

}

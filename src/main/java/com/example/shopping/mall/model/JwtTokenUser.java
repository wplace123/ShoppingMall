package com.example.shopping.mall.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * CONSULT 跟原本的會員model的配對
 * 
 * @author ryan wang
 * @date 2018/12/02
 */
public class JwtTokenUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    private String password;

    private String token;

    private Collection<? extends GrantedAuthority> auth;

    public JwtTokenUser() {

    }

    public JwtTokenUser(AccountModel accountModel, String userToken) {
        id = accountModel.getId();
        userName = accountModel.getUserName();
        password = accountModel.getPwd();
        token = userToken;
        auth = Collections.singleton(new SimpleGrantedAuthority(accountModel.getRole()));

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auth;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

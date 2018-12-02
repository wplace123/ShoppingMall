package com.example.shopping.mall.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.shopping.mall.model.JwtTokenUser;
import com.example.shopping.mall.util.TokenUtils;

/**
 * 驗證username pwd
 * 
 * @author ryan wang
 * @date 2018/12/02
 */
public class AuthFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public AuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // TO UNDERSTANDING
        super.setFilterProcessesUrl("/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
        // return super.attemptAuthentication(request, response);
        // TO UNDERSTANDING UsernamePasswordAuthenticationToken 的參數
        System.err.println("username : " + request.getParameter("username"));
        System.err.println("password : " + request.getParameter("password"));
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            request.getParameter("username"), request.getParameter("password")));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
        Authentication authResult) throws IOException, ServletException {
        System.err.println("in successfulAuthentication method");
        JwtTokenUser jwtTokenUser = (JwtTokenUser)authResult.getPrincipal();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("token", TokenUtils.TOKEN_PREFIX + jwtTokenUser.getToken());
        response.getWriter().write("登入成功");
        System.err.println("out successfulAuthentication method");
        // super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException failed) throws IOException, ServletException {
        System.err.println("in unsuccessfulAuthentication method");
        // super.unsuccessfulAuthentication(request, response, failed);
    }

}

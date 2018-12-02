package com.example.shopping.mall.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.shopping.mall.model.AccountModel;
import com.example.shopping.mall.util.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 驗證token
 * 
 * @author ryan wang
 * @date 2018/12/02
 */
public class VerifyFilter extends BasicAuthenticationFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static ObjectMapper MAPPER = new ObjectMapper();

    public VerifyFilter(AuthenticationManager authenticationManager, ApplicationContext context) {
        super(authenticationManager);
        this.stringRedisTemplate = context.getBean(StringRedisTemplate.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        // 判斷請求中有沒有TOKEN
        String token = request.getHeader(TokenUtils.TOKEN_HEADER);
        if (token == null || token.startsWith(TokenUtils.TOKEN_PREFIX)) {
            response.getWriter().write("token is null");
            return;
        }

        // redis裡面有沒有
        String redisValue = stringRedisTemplate.opsForValue().get(token);
        if (redisValue == null) {
            response.getWriter().write("token is timeout");
            return;
        }

        AccountModel accountModel = MAPPER.readValue(redisValue, AccountModel.class);

        // 這個user有登入過
        ArrayList<Object> authorities = new ArrayList<>();
        // for (String role : redisValue.split(",")) {
        // authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        // }
        authorities.add(new SimpleGrantedAuthority(accountModel.getRole()));

        // 解析username
        SecurityContextHolder.getContext().setAuthentication(getAuth(token, authorities));
        super.doFilterInternal(request, response, chain);
    }

    private UsernamePasswordAuthenticationToken getAuth(String token, ArrayList arrRole) {
        token = token.replaceAll(TokenUtils.TOKEN_PREFIX, "");
        // 把username解析出來
        String username = TokenUtils.getTokenUserName(token);
        // ArrayList authorities = new ArrayList<>();
        // authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (username != null) {
            // 然後再調用UsernamePasswordAuthenticationToken去驗證，並再產生一個token
            // TO UNDERSTANDING 了解參數
            return new UsernamePasswordAuthenticationToken(username, null, arrRole);
        }
        return null;
    }
}

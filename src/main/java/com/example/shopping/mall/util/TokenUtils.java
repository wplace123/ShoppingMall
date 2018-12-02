package com.example.shopping.mall.util;

import java.util.Date;

import com.example.shopping.mall.model.AccountModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author ryan wang
 * @date 2018/12/02
 */
public class TokenUtils {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "testdemo";

    private static final String ISS = "eeeee";

    /**
     * 過期時間
     */
    private static final long EXPIRATION = 3600L;

    private static ObjectMapper MAPPER = new ObjectMapper();

    // TO UNDERSTANDING 裡面塞的參數
    // public static String createToken(String username) {
    // return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET).setIssuer(ISS).setSubject(username)
    // .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000)).compact();
    //
    // }

    public static String createToken(AccountModel accountModel) {
        String json = accountModel.getJsonString(accountModel);
        return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET).setIssuer(ISS).setSubject(json)
            .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000)).compact();

    }

    /**
     * parse
     * 
     * @param token
     * @return
     */
    public static String getTokenUserName(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();

    }
}

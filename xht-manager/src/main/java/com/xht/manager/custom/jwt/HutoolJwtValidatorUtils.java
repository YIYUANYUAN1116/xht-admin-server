package com.xht.manager.custom.jwt;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @Description: 基于hutool jwt 校验实现
* @Param:
* @return:
* @Author: yzd
* @Date: 2023/12/20-9:31
*/
@Component
public class HutoolJwtValidatorUtils implements JwtValidatorUtils{

    private static final Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$",
            Pattern.CASE_INSENSITIVE);

    @Value("${jwt.token}")
    private String token;

    @Value("${jwt.expiration}")
    private Long expireTime;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public String validate(String token) {

        // 验签
        boolean verify = JWTUtil.verify(token, secret.getBytes());
        if (!verify) {
            throw new JwtAuthenticationException("非法令牌");
        }
        // 过期
        final JWT jwt = JWTUtil.parseToken(token);
        long  expireTime = Long.parseLong(jwt.getPayload("expire_time").toString());

        if (System.currentTimeMillis() > expireTime) {
            throw new JwtAuthenticationException("令牌已失效");
        }
        // 返回
        return (String) jwt.getPayload("username");
    }

    @Override
    public String resolve(HttpServletRequest request) {
        String requestToken = request.getHeader(token);
        if (requestToken == null){
            return null;
        }
        String requestTokenHead = request.getHeader("tokenHead");
        Matcher matcher = authorizationPattern.matcher(requestToken);
        if (!matcher.matches() || requestTokenHead.equals(tokenHead)) {
            throw new JwtAuthenticationException("令牌格式错误");
        }
        return matcher.group("token");
    }

    @Override
    public String generateToken(Map<String, Object> jwtMap) {
        return JWTUtil.createToken(jwtMap, secret.getBytes());
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        // JWT信息
        Map<String, Object> jwtMap = new HashMap<>();
        jwtMap.put("username", userDetails.getUsername());// 用户名作为用户唯一标识，实际开发可以使用 用户ID
        jwtMap.put("expire_time", System.currentTimeMillis() + expireTime); // 过期时间，一个小时后过期
        return generateToken(jwtMap);
    }

}

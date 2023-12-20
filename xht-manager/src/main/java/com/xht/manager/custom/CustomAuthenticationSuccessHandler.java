package com.xht.manager.custom;

import com.xht.ResponseUtils;
import com.xht.manager.custom.jwt.HutoolJwtValidatorUtils;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/19  19:54
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private HutoolJwtValidatorUtils hutoolJwtValidator;

    @Value("${jwt.expiration}")
    private Long expireTime;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> result = new HashMap<>(); // 返回结果
        // JWT信息
        Map<String, Object> jwtMap = new HashMap<>();
        jwtMap.put("username", authentication.getName());// 用户名作为用户唯一标识，实际开发可以使用 用户ID
        jwtMap.put("expire_time", System.currentTimeMillis() + expireTime); // 过期时间，一个小时后过期
        String token = hutoolJwtValidator.generateToken(jwtMap);
        result.put("token", token);
        ResponseUtils.buildResponse(response,Result.build(result, ResultCodeEnum.SUCCESS),HttpStatus.OK);
    }
}

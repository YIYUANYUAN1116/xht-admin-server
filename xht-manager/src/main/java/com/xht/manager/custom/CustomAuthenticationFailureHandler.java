package com.xht.manager.custom;

import com.xht.ResponseUtils;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/19  19:55
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ResponseUtils.buildResponse(response,Result.build(exception.getMessage(), ResultCodeEnum.LOGIN_ERROR),HttpStatus.UNAUTHORIZED);
    }
}

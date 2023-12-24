package com.xht.manager.custom;

import com.xht.ResponseUtils;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
* @Description: 自定义无权限拒绝处理器，security默认为重定向403
* @Param:
* @return:
* @Author: yzd
* @Date: 2023/12/20-9:11
*/
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {
        ResponseUtils.buildResponse(response, Result.build(null, ResultCodeEnum.FORBIDDEN), HttpStatus.FORBIDDEN);
    }
}

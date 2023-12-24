package com.xht.manager.custom.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Map;

/**
* @Description: 令牌校验接口
* @Param: 
* @return: 
* @Author: yzd
* @Date: 2023/12/20-9:31
*/
public interface JwtValidatorUtils {
    /**
     * 令牌校验，并返回用户唯一标识
     *
     * @param token 令牌字符串
     * @return 用户唯一标识
     */
    String validate(String token) throws IOException;

    //解析request中的token
    String resolve(HttpServletRequest request);

    //生成token
    String generateToken(Map<String, Object> jwtMap);

    String generateToken(UserDetails userDetails);
}

package com.xht.manager.custom;


import org.springframework.security.core.AuthenticationException;

/**
 * @author : YIYUANYUAN
 * @description : 验证码校验类
 * @date: 2023/12/19  23:13
 */
public class CaptchaVerifyException extends AuthenticationException {
    public CaptchaVerifyException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CaptchaVerifyException(String msg) {
        super(msg);
    }
}

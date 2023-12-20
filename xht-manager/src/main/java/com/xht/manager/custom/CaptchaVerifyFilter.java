package com.xht.manager.custom;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xht.model.entity.system.SysUser;
import com.xht.model.vo.system.UserLoginVo;
import com.xht.model.vo.system.ValidateCodeVo;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/19  23:15
 */
public class CaptchaVerifyFilter extends OncePerRequestFilter {
    // 请求匹配器
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher("/admin/system/index/login", "POST");
    // 用户输入的验证码
    public static final String FORM_CAPTCHA_KEY = "captcha";
    // redis中 验证码Key
    public static final String FORM_CAPTCHA_ID_KEY = "captcha_key";
    // 认证失败处理器
    private final AuthenticationFailureHandler failureHandler;
    // RedisTemplate
    private final RedisTemplate<String,String> redisTemplate;

    public CaptchaVerifyFilter(AuthenticationFailureHandler failureHandler, RedisTemplate<String,String> redisTemplate) {
        this.failureHandler = failureHandler;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 进入用户名密码认证之前，对验证码进行校验
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (DEFAULT_ANT_PATH_REQUEST_MATCHER.matches((HttpServletRequest) request)) {
            // /login请求时，执行验证码校验
            try {
                //获取请求头，据此判断请求参数类型
                String contentType = request.getContentType();
                String captcha = null;
                String captchaKey = null;
                if (MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType) || MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(contentType)) {
                    try {
                        //解析请求体中的 JSON 参数
                        UserLoginVo validateCodeVo = new ObjectMapper().readValue(request.getInputStream(), UserLoginVo.class);
                        if (validateCodeVo != null){
                            captcha = validateCodeVo.getCaptcha();
                            captchaKey = validateCodeVo.getCodeKey();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }else {
                    // 获取请求参数
                    captcha = request.getParameter(FORM_CAPTCHA_KEY);
                    captchaKey = request.getParameter(FORM_CAPTCHA_ID_KEY);

                }
                if (StrUtil.isEmpty(captcha) || StrUtil.isEmpty(captchaKey)) {
                    throw new CaptchaVerifyException("验证码参数为空");
                }
                // 未查询到ID对应的验证码
                String cacheData = redisTemplate.opsForValue().get(captchaKey);
                if (StrUtil.isEmpty(cacheData)) {
                    throw new CaptchaVerifyException("验证码已过期");
                }
                // 校验验证码是否输入正确
                if (!cacheData.equalsIgnoreCase(captcha)) {
                    throw new CaptchaVerifyException("验证码输入错误");
                }
                // 删除验证码...
                redisTemplate.delete(captchaKey);
                filterChain.doFilter(request, response);
            } catch (AuthenticationException e) {
                failureHandler.onAuthenticationFailure((HttpServletRequest) request, (HttpServletResponse) response, e);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }


}

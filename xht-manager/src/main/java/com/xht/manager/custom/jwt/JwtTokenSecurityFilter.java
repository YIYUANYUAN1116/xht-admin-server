package com.xht.manager.custom.jwt;

import cn.hutool.json.JSONException;
import cn.hutool.jwt.JWTException;
import com.alibaba.fastjson2.JSON;
import com.xht.AuthContextUtil;
import com.xht.ResponseUtils;
import com.xht.manager.config.SysUserDetails;
import com.xht.model.entity.system.SysUser;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Data
public class JwtTokenSecurityFilter extends OncePerRequestFilter {

    private static final String FILTER_APPLIED = JwtTokenSecurityFilter.class.getName() + ".APPLIED";


    @Autowired
    private JwtValidatorUtils jwtValidatorUtils ;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public JwtTokenSecurityFilter(){

    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        this.doFilter(request,response, filterChain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (request.getAttribute(FILTER_APPLIED) != null) {
            chain.doFilter(request, response);
        } else {
            String token;
            request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
            // 1. 解析请求中的令牌
            try {
                token = jwtValidatorUtils.resolve(request);
            } catch (JwtAuthenticationException e) {
                ResponseUtils.buildResponse(response, Result.build( null, ResultCodeEnum.LOGIN_AUTH), HttpStatus.UNAUTHORIZED);
                return;
            }
            // 2. 令牌不存在，直接进入下一个过滤器
            if (token == null) {
                chain.doFilter(request, response);
            } else {
                // 3. 存在令牌
                try {
                    // 3.1 校验
                    String username = jwtValidatorUtils.validate(token);
                    // 3.2 根据标识组装用户信息，实际开发可以使用缓存
                    SysUserDetails userDetails = (SysUserDetails) userDetailsService.loadUserByUsername(username);
                    String userJson = redisTemplate.opsForValue().get("user:login" + token);
                    AuthContextUtil.set(JSON.parseObject(userJson, SysUser.class));
                    // 3.3 组装认证信息
                    JwtAuthenticationToken authentication = JwtAuthenticationToken.authenticated(userDetails, token, userDetails.getAuthorities());
                    // 3.3 保存用户信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    chain.doFilter(request, response);
                } catch (JWTException | JSONException | JwtAuthenticationException e) {
                    //e.printStackTrace();
                    ResponseUtils.buildResponse(response,
                            Result.build( null,HttpStatus.UNAUTHORIZED.value(), e.getLocalizedMessage()),
                            HttpStatus.UNAUTHORIZED);
                } finally {
                    request.removeAttribute(FILTER_APPLIED);
                }
            }
        }
    }
}

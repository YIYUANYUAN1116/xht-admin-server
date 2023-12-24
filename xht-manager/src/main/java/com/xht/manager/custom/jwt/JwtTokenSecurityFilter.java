package com.xht.manager.custom.jwt;

import com.alibaba.fastjson2.JSON;
import com.xht.AuthContextUtil;
import com.xht.manager.config.SysUserDetails;
import com.xht.model.vo.common.ResultCodeEnum;
import com.xht.service.exception.CustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Data
@Slf4j
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

            request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
            // 1. 解析请求中的令牌
            String token = request.getHeader("token");
            // 2. 令牌不存在，直接进入下一个过滤器
            if (token == null) {
                chain.doFilter(request, response);
            } else {
                // 3. 存在令牌
                try {
                    // 3.1 校验
//                    String username = jwtValidatorUtils.validate(token);
                    // 3.2 根据标识组装用户信息，实际开发可以使用缓存
//                    SysUserDetails userDetails = (SysUserDetails) userDetailsService.loadUserByUsername(username);
                    String userJson = redisTemplate.opsForValue().get("user:login" + token);
                    SysUserDetails userDetails=JSON.parseObject(userJson, SysUserDetails.class);
                    assert userDetails != null;
                    AuthContextUtil.set(userDetails.getSysUser());
                    // 3.3 组装认证信息
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // 3.3 保存用户信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("token认证通过");
                    chain.doFilter(request, response);
                }catch (Exception e){
                    throw new CustomException(e.getMessage(),ResultCodeEnum.SYSTEM_ERROR2.getCode());
                }finally{
                    request.removeAttribute(FILTER_APPLIED);
                }
            }
        }
    }
}

package com.xht.manager.config;

import com.xht.manager.custom.*;
import com.xht.manager.custom.jwt.JwtTokenSecurityFilter;
import com.xht.service.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/18  21:37
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private String loginUrl;

    private String logoutUrl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //配置除了登录之外的所有请求都要认证
        httpSecurity.authorizeHttpRequests(requests
                -> requests.requestMatchers(
                        "/admin/system/index/generateValidateCode"
                        ,"/admin/system/index/login",
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/*/*.html",
                        "/*/*.css",
                        "/*/*.js",
                        "/swagger-resources/**",
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/swagger-ui/**").permitAll()
                .anyRequest().authenticated());


        //开启basic认证
        httpSecurity.httpBasic(Customizer.withDefaults());


        //添加token过滤器
        httpSecurity.addFilterBefore(jwtTokenSecurityFilter(), UsernamePasswordAuthenticationFilter.class);

        //异常配置
        httpSecurity.exceptionHandling(httpSecurityExceptionHandling -> {
            httpSecurityExceptionHandling.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        });

        //处理跨域
        httpSecurity.cors(httpSecurityCors -> {
            httpSecurityCors.configurationSource(corsConfigurationSource());
        });
        //关闭CSRF
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    //跨域
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");   // 允许所有的请求头
        corsConfiguration.addAllowedMethod("*");    // 允许所有的请求方法
        corsConfiguration.addAllowedOriginPattern("*");  // 允许请求来源的域规则
        corsConfiguration.setAllowCredentials(true);  // 是否允许在跨域的情况下传递Cookie
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // 添加路径规则
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //忽略授权访问路径
        return web -> web.ignoring().requestMatchers("/hello","/admin/system/index/login");
    }

    @Bean
    public JwtTokenSecurityFilter jwtTokenSecurityFilter() {
        return new JwtTokenSecurityFilter();
    }


    @Bean
    public CustomLogoutSuccessHandler customLogoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }
}



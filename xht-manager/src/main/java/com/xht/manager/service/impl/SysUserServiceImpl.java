package com.xht.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.xht.manager.config.SysUserDetails;
import com.xht.manager.custom.jwt.HutoolJwtValidatorUtils;
import com.xht.manager.mapper.SysUserMapper;
import com.xht.manager.service.SysUserService;
import com.xht.model.dto.system.LoginDto;
import com.xht.model.entity.system.SysUser;
import com.xht.model.vo.common.ResultCodeEnum;
import com.xht.model.vo.system.LoginVo;
import com.xht.service.exception.CustomException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/18  20:07
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    SysUserMapper sysUserMapper;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private HutoolJwtValidatorUtils hutoolJwtValidatorUtils;
    @Override
    public SysUser selectUserInfoByUserName(String username) {
        return sysUserMapper.selectUserInfoByUserName(username);
    }

    @Override
    public String login(String userName, String password) {
        String token = null;
        try {
            SysUserDetails userDetails = (SysUserDetails) userDetailsService.loadUserByUsername(userName);
            if (!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = hutoolJwtValidatorUtils.generateToken(userDetails);

            //8 把登录成功用户信息放到redis里面
            // key : token   value: 用户信息

            redisTemplate.opsForValue()
                    .set("user:login"+token,
                            JSON.toJSONString(userDetails),
                            7,
                            TimeUnit.DAYS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }


}

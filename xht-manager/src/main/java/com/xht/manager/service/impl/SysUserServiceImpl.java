package com.xht.manager.service.impl;

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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private HutoolJwtValidatorUtils hutoolJwtValidatorUtils;
    @Override
    public SysUser selectUserInfoByUserName(String username) {
        return sysUserMapper.selectUserInfoByUserName(username);
    }

    @Override
    public String login(String userName, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            if (!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = hutoolJwtValidatorUtils.generateToken(userDetails);
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }


}

package com.xht.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
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
    public String login(LoginDto loginDto) {
        //1.验证码校验
        String captcha = loginDto.getCaptcha();
        String captchaKey = loginDto.getCodeKey();

        if (StrUtil.isEmpty(captcha) || StrUtil.isEmpty(captchaKey)) {
            throw new CustomException(ResultCodeEnum.VALIDATE_CODE_ERROR);
        }

        String cacheData = redisTemplate.opsForValue().get(captchaKey);
        if (StrUtil.isEmpty(cacheData)) {
            // 未查询到ID对应的验证码
            throw new CustomException(ResultCodeEnum.VALIDATE_CODE_EXPIRED);
        }
        // 校验验证码是否输入正确
        if (!cacheData.equalsIgnoreCase(captcha)) {
            throw new CustomException(ResultCodeEnum.VALIDATE_CODE_ERROR);
        }
        // 删除验证码...
        redisTemplate.delete(captchaKey);


        //登录校验
        String userName = loginDto.getUserName();
        String password = loginDto.getPassword();

        if (StrUtil.isEmpty(userName) || StrUtil.isEmpty(password)) {
            throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
        }

        String token = null;
        try {
            SysUserDetails userDetails = (SysUserDetails) userDetailsService.loadUserByUsername(loginDto.getUserName());
            if (!passwordEncoder.matches(loginDto.getPassword(),userDetails.getPassword())){
                throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = hutoolJwtValidatorUtils.generateToken(userDetails);

            String jsonString = JSON.toJSONString(userDetails, JSONWriter.Feature.NullAsDefaultValue);
            // 把登录成功用户信息放到redis里面
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

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login" + token);
    }

}

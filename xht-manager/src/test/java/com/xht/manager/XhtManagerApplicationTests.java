package com.xht.manager;

import com.xht.manager.custom.jwt.JwtValidatorUtils;
import com.xht.manager.mapper.SysUserMapper;
import com.xht.model.entity.system.SysUser;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;

@SpringBootTest
class XhtManagerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    SysUserMapper sysUserMapper;
    @Test
    public void insertUser(){
        SysUser sysUser = sysUserMapper.selectById(1);

        SysUser sysUserInsert = new SysUser();
        BeanUtils.copyProperties(sysUser,sysUserInsert);
        String encode = new BCryptPasswordEncoder().encode("123456");
        sysUserInsert.setPassword(new BCryptPasswordEncoder().encode("123456"));
        sysUserInsert.setName("test1");
        sysUserInsert.setUserName("test1");
        sysUserInsert.setId(null);
        sysUserMapper.insert(sysUserInsert);
    }

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    JwtValidatorUtils jwtValidatorUtils;

    @Test
    public void testRedis(){
        String s = jwtValidatorUtils.generateToken(new HashMap<>());
        stringRedisTemplate.opsForValue().set("test","test");
    }
}

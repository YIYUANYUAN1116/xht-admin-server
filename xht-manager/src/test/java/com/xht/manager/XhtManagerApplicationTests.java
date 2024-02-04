package com.xht.manager;

import com.alibaba.fastjson2.JSON;
import com.xht.manager.custom.jwt.JwtValidatorUtils;
import com.xht.manager.mapper.SysUserMapper;
import com.xht.manager.service.FileUploadService;
import com.xht.model.entity.system.SysUser;
import com.xht.model.vo.system.SysUserVo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        System.out.println(encode);
    }

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    JwtValidatorUtils jwtValidatorUtils;

    @Test
    public void testRedis(){
        SysUser sysUser = new SysUser();
        sysUser.setName("test");
        String jsonString = JSON.toJSONString(sysUser);
        SysUser sysUser1 = JSON.parseObject(jsonString, SysUser.class);
        System.out.println("123");
    }

    @Resource
    FileUploadService fileUploadService;

    @Test
    public void delete(){
//        String str = "20231226/aa6c51dbb4364b6ab279ac972c929772R.png";
//        fileUploadService.delete(str);
        String str = "http://123.60.189.149:9001/xht-bucket/20231226/af390e96253c403a9a49173c2ea86d0cth.jpg";
        int i = str.indexOf("xht-bucket/");
        String substring = str.substring(i+"xht-bucket/".length());
        System.out.println(substring);
    }

    @Test
    public void trans(){
        Long Lo = 1705507200000L;
        // 手动进行日期转换
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Lo);
        Date parsedDate;
//        try {
//            parsedDate = dateFormat.parse(Lo);
//        } catch (ParseException e) {
//            throw new IllegalArgumentException("Invalid date format", e);
//        }

        System.out.println(date);
    }


}

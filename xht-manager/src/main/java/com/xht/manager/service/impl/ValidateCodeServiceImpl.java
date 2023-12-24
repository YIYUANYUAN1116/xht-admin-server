package com.xht.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.xht.manager.service.ValidateCodeService;
import com.xht.model.vo.system.ValidateCodeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/19  21:02
 */
@Service
@Slf4j
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public ValidateCodeVo generateValidateCode() {
        //1.生成验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        String codeValue = circleCaptcha.getCode();//4位验证码值
        String imageBase64 = circleCaptcha.getImageBase64(); //返回图片验证码，base64编码
        log.info(codeValue);
        //2.存入redis
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String key = "user:validate:"+uuid;
        redisTemplate.opsForValue().set(key,
                codeValue,5, TimeUnit.MINUTES);

        //3.返回ValidateCodeVo
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(key);
        validateCodeVo.setCodeValue("data:image/png;base64,"+imageBase64);

        return validateCodeVo;
    }
}

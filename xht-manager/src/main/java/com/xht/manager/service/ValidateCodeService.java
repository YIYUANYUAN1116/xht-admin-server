package com.xht.manager.service;

import com.xht.model.vo.system.ValidateCodeVo;

public interface ValidateCodeService {

    //生成图片验证码
    ValidateCodeVo generateValidateCode();
}

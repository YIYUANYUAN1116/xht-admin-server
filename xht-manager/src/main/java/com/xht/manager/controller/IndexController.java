package com.xht.manager.controller;

import com.xht.AuthContextUtil;
import com.xht.manager.service.SysUserService;
import com.xht.manager.service.ValidateCodeService;
import com.xht.model.dto.system.LoginDto;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import com.xht.model.vo.system.SysMenuVo;
import com.xht.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "登录接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;



    @Value("${jwt.token}")
    private String token;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    //用户退出
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.logout(token);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


    @GetMapping(value = "/getUserInfo")
    public Result getUserInfo() {
        return Result.build(AuthContextUtil.get(),ResultCodeEnum.SUCCESS);
    }



    //生成图片验证码
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo,ResultCodeEnum.SUCCESS);
    }


    //用户登录
    @Operation(summary = "登录的方法")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        String token = sysUserService.login(loginDto);
        if (token == null) {
            return Result.build(ResultCodeEnum.LOGIN_ERROR);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Result.build(tokenMap, ResultCodeEnum.SUCCESS);
    }


}

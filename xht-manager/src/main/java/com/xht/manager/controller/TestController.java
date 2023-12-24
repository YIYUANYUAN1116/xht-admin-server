package com.xht.manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/19  18:08
 */

@RestController
public class TestController {

    @Operation(summary = "登录接口")
    @GetMapping("/test")
    public String testC(){
        return "test";
    }

    @Operation(summary = "登录接口")
    @GetMapping("/hello")
    public String testB(){
        return "hello";
    }
}

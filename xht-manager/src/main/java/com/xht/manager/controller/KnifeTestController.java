package com.xht.manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "KnifeTestController")
public class KnifeTestController {

    @Operation(description = "test")
    @GetMapping("test")
    public String test(){
        return "hello";
    }
}

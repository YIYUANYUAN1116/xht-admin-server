package com.xht.manager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xht.manager.service.SysOperLogService;
import com.xht.model.entity.product.Brand;
import com.xht.model.entity.system.SysOperLog;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author : YIYUANYUAN
 * @date: 2024/1/15  21:57
 */
@Tag(name = "日志接口")
@RestController
@RequestMapping("/admin/log")
public class LogController {

    @Autowired
    SysOperLogService sysOperLogService;

    @Operation(summary = "分页日志")
    @GetMapping("/{page}/{limit}/")
    public Result list(@PathVariable Integer page,
                       @PathVariable Integer limit,
                       @RequestParam(required = false) Date date){
        Page<SysOperLog> pageInfo = sysOperLogService.listPage(page, limit,date);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}

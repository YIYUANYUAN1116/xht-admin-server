package com.xht.manager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xht.manager.service.SysOperLogService;
import com.xht.model.entity.product.Brand;
import com.xht.model.entity.system.SysOperLog;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    //YYYY-MM-DD HH:mm:ss
    @Operation(summary = "分页日志")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page,
                       @PathVariable Integer limit,
                       @RequestParam(required = false) Long startDate,
                       @RequestParam(required = false) Long endDate){

        //todo 如何处理前后端date
        Date startTime = null;
        Date endTime = null;
        if (startDate!=null){
            startTime= new Date(startDate);
        }
        if (endDate != null){
            endTime= new Date(endDate);
        }


        Page<SysOperLog> pageInfo = sysOperLogService.listPage(page, limit,startTime,endTime);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}

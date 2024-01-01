package com.xht.manager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xht.log.annotation.XhtLog;
import com.xht.manager.service.SysUserService;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import com.xht.model.vo.system.DoAssignUserRole;
import com.xht.model.vo.system.ToAssignUserVo;
import com.xht.model.vo.system.SysUserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/28  14:39
 */

@Tag(name = "用户管理")
@RestController()
@RequestMapping("/admin/acl/user")
public class UserController {

    @Autowired
    SysUserService sysUserService;

    @XhtLog(title = "获取全部用户",businessType = 0)
    @Operation(summary = "获取全部用户")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page,
                       @PathVariable Integer limit,
                       @RequestParam(required = false) String userName){
       Page<SysUserVo> result = sysUserService.getAll(page,limit,userName);
       return Result.build(result, ResultCodeEnum.SUCCESS);
    }


    @XhtLog(title = "新增用户",businessType = 0)
    @Operation(summary = "新增用户")
    @PostMapping
    public Result save(@RequestBody SysUserVo sysUserVo){
        sysUserService.saveOrUpdateVo(sysUserVo);
        return Result.build( ResultCodeEnum.SUCCESS);
    }

    @XhtLog(title = "更新用户",businessType = 0)
    @Operation(summary = "更新用户")
    @PutMapping
    public Result update(@RequestBody SysUserVo sysUserVo){
        sysUserService.saveOrUpdateVo(sysUserVo);
        return Result.build( ResultCodeEnum.SUCCESS);
    }

    @XhtLog(title = "删除用户",businessType = 0)
    @Operation(summary = "删除用户")
    @DeleteMapping("/{userId}")
    public Result remove(@PathVariable Long userId){
        sysUserService.removeByIdWithRe(userId);
        return Result.build( ResultCodeEnum.SUCCESS);
    }

    @XhtLog(title = "批量删除用户",businessType = 0)
    @Operation(summary = "批量删除用户")
    @DeleteMapping
    public Result remove(@RequestBody List<Long> idlist){
        sysUserService.removeBatchByIdsWithRe(idlist);
        return Result.build( ResultCodeEnum.SUCCESS);
    }

    @XhtLog(title = "获取用户权限和所有权限",businessType = 0)
    @Operation(summary = "获取用户权限和所有权限")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId){
        ToAssignUserVo toAssignUserVo = sysUserService.toAssign(userId);
        return Result.build(toAssignUserVo, ResultCodeEnum.SUCCESS);
    }
    @XhtLog(title = "分配角色",businessType = 0)
    @Operation(summary = "分配角色")
    @PostMapping("/doAssignRole")
    public Result doAssignRole(@RequestBody DoAssignUserRole doAssignUserRole){
         sysUserService.doAssignRole(doAssignUserRole);
        return Result.build(ResultCodeEnum.SUCCESS);
    }

}

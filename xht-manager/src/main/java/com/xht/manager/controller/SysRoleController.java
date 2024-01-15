package com.xht.manager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xht.manager.service.SysRoleService;
import com.xht.model.entity.system.SysRole;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import com.xht.model.vo.system.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
@RestController
@RequestMapping("/admin/acl/role")
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;

    @Operation(summary = "获取全部角色")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page,
                       @PathVariable Integer limit,
                       @RequestParam(required = false) String roleName){
        Page<SysRole> result = sysRoleService.getAll(page,limit,roleName);
        return Result.build(result, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "保存角色")
    @PostMapping
    public Result save(@RequestBody SysRole sysRole){
        sysRoleService.saveOrUpdate(sysRole);
        return Result.build( ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "更新角色")
    @PutMapping
    public Result update(@RequestBody SysRole sysRole){
        sysRoleService.saveOrUpdate(sysRole);
        return Result.build( ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{sysRoleId}")
    public Result delete(@PathVariable Long sysRoleId){
        sysRoleService.removeByIdWithRe(sysRoleId);
        return Result.build( ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "获取角色权限")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId){
        ToAssignMenuVo toAssignUserVo = sysRoleService.toAssign(roleId);
        return Result.build(toAssignUserVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "分配权限")
    @PostMapping("/doAssign")
    public Result doAssignRole(@RequestBody DoAssignMenuVo doAssignMenuVo){
        sysRoleService.doAssignRole(doAssignMenuVo);
        return Result.build(ResultCodeEnum.SUCCESS);
    }


}

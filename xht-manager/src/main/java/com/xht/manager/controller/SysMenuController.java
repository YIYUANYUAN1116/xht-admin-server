package com.xht.manager.controller;

import com.xht.manager.service.SysMenuService;
import com.xht.manager.utils.BuildTree;
import com.xht.model.entity.system.SysMenu;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
@RestController
@RequestMapping("/admin/acl/permission")
@Tag(name = "菜单管理")
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;


    @GetMapping
    @Operation(summary = "获取全部菜单")
    public Result list(){
        List<SysMenu> list = sysMenuService.list();
        return Result.build(BuildTree.buildMenuTree(list),ResultCodeEnum.SUCCESS);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "删除菜单")
    public Result remove(@PathVariable Long id){
        sysMenuService.removeId(id);
        return Result.build(ResultCodeEnum.SUCCESS);
    }

    @PostMapping()
    @Operation(summary = "保存菜单")
    public Result save(@RequestBody SysMenu sysMenu){
        sysMenuService.saveOrUpdate(sysMenu);
        return Result.build(ResultCodeEnum.SUCCESS);
    }

    @PutMapping()
    @Operation(summary = "更新菜单")
    public Result update(@RequestBody SysMenu sysMenu){
        sysMenuService.saveOrUpdate(sysMenu);
        return Result.build(ResultCodeEnum.SUCCESS);
    }
}

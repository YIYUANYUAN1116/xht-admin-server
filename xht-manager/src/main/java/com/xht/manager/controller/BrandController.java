package com.xht.manager.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xht.manager.service.BrandService;
import com.xht.model.entity.product.Brand;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/admin/product/brand")
@Tag(name = "品牌接口")
public class BrandController {

    @Autowired
    private BrandService brandService;

    //查询所有品牌
    @Operation(summary = "查询所有")
    @GetMapping()
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "分页查询")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page,
                       @PathVariable Integer limit) {
        Page<Brand> pageInfo = brandService.findByPage(page, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    //添加
    @Operation(summary = "保存")
    @PostMapping()
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "更新")
    @PutMapping()
    public Result update(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public Result delete(Integer id) {
        brandService.delete(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}

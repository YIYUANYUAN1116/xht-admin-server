package com.xht.manager.controller;

import com.xht.manager.service.CategoryService;
import com.xht.manager.utils.BuildCategoryTree;
import com.xht.model.entity.product.Category;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品分类 前端控制器
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
@RestController
@RequestMapping("admin/product/category")
@Tag(name = "商品分类接口")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Operation(summary = "获取商品分类")
    @GetMapping("/{parentId}")
    public Result getCategory(@PathVariable(required = false) Long parentId){
        List<Category> list = categoryService.getByParent(parentId);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "新增分类")
    @PostMapping()
    public Result save(@RequestBody Category category){
        categoryService.saveOrUpdate(category);
        return  Result.build(category,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "更新分类")
    @PutMapping()
    public Result update(@RequestBody Category category){
        categoryService.saveOrUpdate(category);
        return  Result.build(category,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除分类")
    @DeleteMapping("/{id}")
    public Result update(@PathVariable Integer id){
        categoryService.removeById(id);
        return  Result.build(null,ResultCodeEnum.SUCCESS);
    }
}

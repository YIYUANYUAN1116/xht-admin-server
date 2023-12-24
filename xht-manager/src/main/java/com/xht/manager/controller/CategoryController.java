package com.xht.manager.controller;

import com.xht.manager.service.CategoryService;
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
}

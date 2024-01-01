package com.xht.manager.controller;

import com.xht.manager.mapper.CategoryAttrMapper;
import com.xht.manager.service.CategoryAttrService;
import com.xht.model.entity.product.CategoryAttr;
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
 *  前端控制器
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
@RestController
@RequestMapping("/admin/product/attr")
@Tag(name = "分类属性接口")
public class CategoryAttrController {

    @Autowired
    private CategoryAttrService categoryAttrService;

    @Operation(summary = "根据category获取attr")
    @GetMapping("/{category1Id}/{category2Id}/{category3Id}")
    public Result getAttrByCategory(@PathVariable Long category1Id,@PathVariable Long category2Id,@PathVariable Long category3Id){
        List<CategoryAttr> attrByCategoryId = categoryAttrService.getAttrByCategoryId(category1Id,category2Id,category3Id);
        return Result.build(attrByCategoryId, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "根据category获取attr")
    @GetMapping("/{category3Id}")
    public Result getAttrByCategory(@PathVariable Long category3Id){
        List<CategoryAttr> attrByCategoryId = categoryAttrService.getAttrByCategory3Id(category3Id);
        return Result.build(attrByCategoryId, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "新增")
    @PostMapping()
    public Result saveAttr(@RequestBody CategoryAttr attr){
        categoryAttrService.saveAttr(attr);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "新增")
    @DeleteMapping("/{attrId}")
    public Result saveAttr(@PathVariable Long attrId){
        categoryAttrService.deleted(attrId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}

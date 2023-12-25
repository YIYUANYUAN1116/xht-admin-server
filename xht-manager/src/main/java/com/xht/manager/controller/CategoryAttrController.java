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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/{categoryId}")
    public Result getAttrByCategory(@PathVariable Long categoryId){
        List<CategoryAttr> attrByCategoryId = categoryAttrService.getAttrByCategoryId(categoryId);
        return Result.build(attrByCategoryId, ResultCodeEnum.SUCCESS);
    }
}

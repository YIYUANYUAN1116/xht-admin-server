package com.xht.manager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xht.manager.service.SkuService;
import com.xht.model.dto.product.SkuDto;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import com.xht.model.vo.product.SkuVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xht
 * @since 2023-12-27
 */
@RestController
@RequestMapping("/admin/product/sku")
@Tag(name = "sku接口")
public class SkuController {

    @Autowired
    SkuService skuService;


    @GetMapping("/list/{page}/{limit}")
    @Operation(summary = "list")
    public Result list(@PathVariable Integer page,@PathVariable Integer limit){
        Page<SkuVo> result =  skuService.listAll(page,limit);
        return Result.build(result,ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/cancelSale/{skuId}")
    @Operation(summary = "下架")
    public Result cancelSale(@PathVariable Long skuId){
        skuService.cancelSale(skuId);
        return Result.build(ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/onSale/{skuId}")
    @Operation(summary = "上架")
    public Result onSale(@PathVariable Long skuId){
        skuService.onSale(skuId);
        return Result.build(ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/{skuId}")
    @Operation(summary = "获取skuInfo")
    public Result getSkuInfo(@PathVariable Long skuId){
        SkuVo skuInfo = skuService.getSkuInfo(skuId);
        return Result.build(skuInfo,ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/{skuId}")
    @Operation(summary = "删除sku")
    public Result delete(@PathVariable Long skuId){
        skuService.deleteSku(skuId);
        return Result.build(ResultCodeEnum.SUCCESS);
    }

    @PostMapping
    @Operation(summary = "sku保存")
    public Result save(@RequestBody SkuDto skuDto){
        skuService.saveSkuDto(skuDto);
        return Result.build(ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/findBySpuId/{spuId}")
    @Operation(summary = "findBySpuId")
    public Result findBySpuId(@PathVariable Long spuId){
      List<SkuVo>  result =  skuService.findBySpuId(spuId);
      return Result.build(result,ResultCodeEnum.SUCCESS);
    }

}

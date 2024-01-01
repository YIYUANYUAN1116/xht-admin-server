package com.xht.manager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xht.manager.service.SpuService;
import com.xht.model.dto.product.SpuDto;
import com.xht.model.entity.product.Spu;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xht
 * @since 2023-12-26
 */
@RestController
@Tag(name = "spu接口")
@RequestMapping("/admin/product/spu")
public class SpuController {

    @Autowired
    SpuService spuService;

    @Operation(summary = "分页查询")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page,@PathVariable Integer limit,@RequestParam Long category3Id){
        Page<Spu> pageInfo = spuService.listPage(page, limit,category3Id);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "保存spu")
    @PostMapping
    public Result save(@RequestBody SpuDto spuDto){
        spuService.saveOrUpdateSpuDto(spuDto);
        return Result.build(ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "更新spu")
    @PutMapping
    public Result update(@RequestBody SpuDto spuDto){
        spuService.saveOrUpdateSpuDto(spuDto);
        return Result.build(ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除spu")
    @DeleteMapping
    public Result delete(@PathVariable Long spuId){
        //TODO 删除
        return Result.build(ResultCodeEnum.SUCCESS);
    }
}

package com.xht.manager.controller;

import com.xht.manager.service.SpuImageService;
import com.xht.model.entity.product.SpuImage;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
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
 * @since 2023-12-26
 */
@RestController
@RequestMapping("/admin/product/spuImage")
@Tag(name = "SpuImage接口")
public class SpuImageController {

    @Autowired
    private SpuImageService spuImageService;

    @GetMapping("/{spuId}")
    @Operation(summary = "获取spu图片")
    public Result getBySpuId(@PathVariable Long spuId){
      List<SpuImage> spuImages =  spuImageService.getBySpuId(spuId);
      return Result.build(spuImages, ResultCodeEnum.SUCCESS);
    }


    @PostMapping("/delete")
    @Operation(summary = "删除")
    public Result remove(@RequestBody SpuImage spuImage){
        spuImageService.delete(spuImage);
        return Result.build(ResultCodeEnum.SUCCESS);
    }
}

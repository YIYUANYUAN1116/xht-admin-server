package com.xht.manager.controller;

import com.xht.manager.mapper.SpuAttrMapper;
import com.xht.manager.service.SpuAttrService;
import com.xht.model.entity.product.SpuAttr;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2023-12-26
 */
@RestController
@RequestMapping("/admin/product/spu/attr")
@Tag(name = "SpuAttr接口")
public class SpuAttrController {

    @Autowired
    private SpuAttrService spuAttrService;

    @GetMapping("/{spuId}")
    @Operation(summary = "根据spuId获取spuAttr")
    public Result getAttrBySpu(@PathVariable Long spuId){
        List<SpuAttr> spuAttrList = spuAttrService.getAttrBySpu(spuId);
        return Result.build(spuAttrList, ResultCodeEnum.SUCCESS);
    }
}

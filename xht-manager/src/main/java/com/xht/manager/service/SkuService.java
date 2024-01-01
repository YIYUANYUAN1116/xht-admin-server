package com.xht.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.dto.product.SkuDto;
import com.xht.model.entity.product.Sku;
import com.xht.model.vo.product.SkuVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-27
 */
public interface SkuService extends IService<Sku> {

    void saveSkuDto(SkuDto skuDto);

    List<SkuVo> findBySpuId(Long spuId);

    Page<SkuVo> listAll(Integer page, Integer limit);

    void onSale(Long skuId);

    void cancelSale(Long skuId);

    SkuVo getSkuInfo(Long skuId);

    void deleteSku(Long skuId);
}

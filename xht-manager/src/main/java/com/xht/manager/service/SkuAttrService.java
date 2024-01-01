package com.xht.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.product.SkuAttr;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-27
 */
public interface SkuAttrService extends IService<SkuAttr> {

    List<SkuAttr> getBySkuId(Long id);

    void deleteBySkuId(Long skuId);
}

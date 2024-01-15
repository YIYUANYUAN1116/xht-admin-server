package com.xht.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.product.SkuImg;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-27
 */
public interface SkuImgService extends IService<SkuImg> {

    void deleteBySkuId(Long skuId);

    List<SkuImg> getBySkuId(Long id);
}

package com.xht.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.product.SpuImage;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-26
 */
public interface SpuImageService extends IService<SpuImage> {

    List<SpuImage> getBySpuId(Long spuId);

    void delete(SpuImage spuImage);
}

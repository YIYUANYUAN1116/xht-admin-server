package com.xht.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.product.SpuAttr;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-26
 */
public interface SpuAttrService extends IService<SpuAttr> {

    List<SpuAttr> getAttrBySpu(Long spuId);

    void savaSpuAttr(List<SpuAttr> spuSaleAttrList, Long id);
}

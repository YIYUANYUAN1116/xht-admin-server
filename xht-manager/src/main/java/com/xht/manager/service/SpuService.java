package com.xht.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.dto.product.SpuDto;
import com.xht.model.entity.product.Spu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-26
 */
public interface SpuService extends IService<Spu> {

    Page<Spu> listPage(Integer page, Integer limit,Long category3Id);

    void saveOrUpdateSpuDto(SpuDto spuDto);

}

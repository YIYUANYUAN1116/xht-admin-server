package com.xht.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xht.model.entity.product.Sku;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xht
 * @since 2023-12-27
 */
public interface SkuMapper extends BaseMapper<Sku> {

    List<Sku> selectAll();
}

package com.xht.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xht.model.entity.product.Brand;

import java.util.List;

/**
 * <p>
 * 分类品牌 Mapper 接口
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
public interface BrandMapper extends BaseMapper<Brand> {

    List<Brand> selectAll();
}

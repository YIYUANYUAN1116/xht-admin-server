package com.xht.manager.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xht.model.entity.product.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品分类 Mapper 接口
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> selectCategoryByParentId(@Param("id") Long id);
}

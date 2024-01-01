package com.xht.manager.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xht.model.entity.product.CategoryAttrValue;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
public interface CategoryAttrValueMapper extends BaseMapper<CategoryAttrValue> {

    void deleteByAttrId(@Param("attrId") Long attrId);
}

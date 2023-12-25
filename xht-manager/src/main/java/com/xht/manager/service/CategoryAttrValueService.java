package com.xht.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.product.CategoryAttrValue;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
public interface CategoryAttrValueService extends IService<CategoryAttrValue> {

    List<CategoryAttrValue> getByAttrId(Long id);
}

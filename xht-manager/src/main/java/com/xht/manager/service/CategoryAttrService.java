package com.xht.manager.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.product.CategoryAttr;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
public interface CategoryAttrService extends IService<CategoryAttr> {
    List<CategoryAttr> getAttrByCategoryId(Long category);
}

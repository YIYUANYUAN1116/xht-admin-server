package com.xht.manager.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.product.Category;

import java.util.List;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
public interface CategoryService extends IService<Category> {

    List<Category> getByParent(Long parentId);
}

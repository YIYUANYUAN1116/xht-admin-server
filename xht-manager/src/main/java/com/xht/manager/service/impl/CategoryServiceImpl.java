package com.xht.manager.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.manager.mapper.CategoryMapper;
import com.xht.manager.service.CategoryService;
import com.xht.model.entity.product.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getByParent(Long parentId) {
        List<Category> categories = categoryMapper.selectCategoryByParentId(parentId);
        return categories;
    }
}

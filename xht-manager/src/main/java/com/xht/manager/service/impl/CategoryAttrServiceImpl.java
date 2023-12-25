package com.xht.manager.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.manager.mapper.CategoryAttrMapper;
import com.xht.manager.service.CategoryAttrService;
import com.xht.manager.service.CategoryAttrValueService;
import com.xht.model.entity.product.CategoryAttr;
import com.xht.model.entity.product.CategoryAttrValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
@Service
public class CategoryAttrServiceImpl extends ServiceImpl<CategoryAttrMapper, CategoryAttr> implements CategoryAttrService {

    @Autowired
    private CategoryAttrMapper categoryAttrMapper;

    @Autowired
    private CategoryAttrValueService categoryAttrValueService;

    @Override
    public List<CategoryAttr> getAttrByCategoryId(Long category) {
        List<CategoryAttr> categoryAttrs = categoryAttrMapper.selectList(new LambdaQueryWrapper<CategoryAttr>().eq(CategoryAttr::getCategoryId, category));
        for (CategoryAttr categoryAttr : categoryAttrs) {
            List<CategoryAttrValue> categoryAttrValues = categoryAttrValueService.getByAttrId(categoryAttr.getId());
            categoryAttr.setCategoryAttrValues(categoryAttrValues);
        }
        return categoryAttrs;
    }
}

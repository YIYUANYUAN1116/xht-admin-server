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
    public List<CategoryAttr> getAttrByCategoryId(Long category1Id,Long category2Id,Long category3Id) {
        List<CategoryAttr> categoryAttrs = categoryAttrMapper.selectList(new LambdaQueryWrapper<CategoryAttr>().eq(CategoryAttr::getCategoryId, category1Id)
                .or().eq(CategoryAttr::getCategoryId, category2Id).or().eq(CategoryAttr::getCategoryId, category3Id));
        for (CategoryAttr categoryAttr : categoryAttrs) {
            List<CategoryAttrValue> categoryAttrValues = categoryAttrValueService.getByAttrId(categoryAttr.getId());
            categoryAttr.setAttrValueList(categoryAttrValues);
        }
        return categoryAttrs;
    }

    @Override
    public void saveAttr(CategoryAttr attr) {
        //新增
        if (attr.getId() == null){
            categoryAttrMapper.insert(attr);
            List<CategoryAttrValue> attrValueList = attr.getAttrValueList();
            for (CategoryAttrValue categoryAttrValue : attrValueList) {
                categoryAttrValue.setAttrId(attr.getId());
                categoryAttrValueService.save(categoryAttrValue);
            }
        }else {
            //更新
            categoryAttrMapper.updateById(attr);
            List<CategoryAttrValue> attrValueList = attr.getAttrValueList();
            for (CategoryAttrValue categoryAttrValue : attrValueList) {
                if (categoryAttrValue.getId() == null){
                    categoryAttrValue.setAttrId(attr.getId());
                    categoryAttrValueService.save(categoryAttrValue);
                }else {
                    categoryAttrValueService.updateById(categoryAttrValue);
                }
            }
        }
    }

    @Override
    public void deleted(Long attrId) {
        categoryAttrMapper.deleteById(attrId);
        categoryAttrValueService.deletedByAttrId(attrId);
    }

    @Override
    public List<CategoryAttr> getAttrByCategory3Id(Long category3Id) {
        List<CategoryAttr> categoryAttrs = categoryAttrMapper.selectList(new LambdaQueryWrapper<CategoryAttr>().eq(CategoryAttr::getCategoryId, category3Id));
        for (CategoryAttr categoryAttr : categoryAttrs) {
            List<CategoryAttrValue> categoryAttrValues = categoryAttrValueService.getByAttrId(categoryAttr.getId());
            categoryAttr.setAttrValueList(categoryAttrValues);
        }
        return categoryAttrs;
    }
}

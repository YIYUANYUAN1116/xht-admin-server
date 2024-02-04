package com.xht.manager.utils;

import com.xht.model.entity.product.Category;
import com.xht.model.entity.system.SysMenu;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : YIYUANYUAN
 * @date: 2024/2/4  16:24
 */
public class BuildCategoryTree {
    public static List<Category> buildMenuTree(List<Category> categoryList){
        List<Category> parent = categoryList.stream().filter(category -> category.getParentId() == 0).collect(Collectors.toList());
        for (Category category : parent) {
            category.setChildren(findChildren(category,categoryList));
        }
        return parent;
    }

    private static List<Category> findChildren(Category categoryParent,List<Category> categoryList){
        List<Category> collect = categoryList.stream().filter(category -> Objects.equals(category.getParentId(), categoryParent.getId())).collect(Collectors.toList());
        for (Category category : collect) {
            category.setChildren(findChildren(category,categoryList));
        }
        return collect;
    }
}

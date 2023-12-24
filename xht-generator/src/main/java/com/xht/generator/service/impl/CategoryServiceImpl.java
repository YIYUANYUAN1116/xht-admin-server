package com.xht.generator.service.impl;

import com.xht.generator.entity.Category;
import com.xht.generator.mapper.CategoryMapper;
import com.xht.generator.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

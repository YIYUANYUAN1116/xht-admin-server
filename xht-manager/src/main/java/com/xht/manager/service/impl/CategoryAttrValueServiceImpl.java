package com.xht.manager.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.manager.mapper.CategoryAttrValueMapper;
import com.xht.manager.service.CategoryAttrValueService;
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
public class CategoryAttrValueServiceImpl extends ServiceImpl<CategoryAttrValueMapper, CategoryAttrValue> implements CategoryAttrValueService {

    @Autowired
    private CategoryAttrValueMapper categoryAttrValueMapper;

    @Override
    public List<CategoryAttrValue> getByAttrId(Long attrId) {
        return  categoryAttrValueMapper.selectList(new LambdaQueryWrapper<CategoryAttrValue>().eq(CategoryAttrValue::getAttrId, attrId));
    }
}

package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.model.entity.product.SkuAttr;
import com.xht.manager.mapper.SkuAttrMapper;
import com.xht.manager.service.SkuAttrService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xht
 * @since 2023-12-27
 */
@Service
public class SkuAttrServiceImpl extends ServiceImpl<SkuAttrMapper, SkuAttr> implements SkuAttrService {

    @Override
    public List<SkuAttr> getBySkuId(Long skuId) {
        return baseMapper.selectList(new LambdaQueryWrapper<SkuAttr>().eq(SkuAttr::getSkuId,skuId)
                .eq(SkuAttr::getIsDeleted,0));
    }

    @Override
    public void deleteBySkuId(Long skuId) {
        baseMapper.delete(new LambdaQueryWrapper<SkuAttr>().eq(SkuAttr::getSkuId,skuId));
    }
}

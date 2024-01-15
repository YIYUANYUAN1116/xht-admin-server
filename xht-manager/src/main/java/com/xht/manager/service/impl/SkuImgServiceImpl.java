package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.model.entity.product.SkuImg;
import com.xht.manager.mapper.SkuImgMapper;
import com.xht.manager.service.SkuImgService;
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
public class SkuImgServiceImpl extends ServiceImpl<SkuImgMapper, SkuImg> implements SkuImgService {

    @Override
    public void deleteBySkuId(Long skuId) {
        baseMapper.delete(new LambdaQueryWrapper<SkuImg>().eq(SkuImg::getSkuId,skuId));
    }

    @Override
    public List<SkuImg> getBySkuId(Long skuId) {
        return baseMapper.selectList(new LambdaQueryWrapper<SkuImg>().eq(SkuImg::getSkuId,skuId));
    }
}

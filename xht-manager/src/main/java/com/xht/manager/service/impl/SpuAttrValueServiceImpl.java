package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.manager.mapper.SpuAttrValueMapper;
import com.xht.manager.service.SpuAttrValueService;
import com.xht.model.entity.product.SpuAttrValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xht
 * @since 2023-12-26
 */
@Service
public class SpuAttrValueServiceImpl extends ServiceImpl<SpuAttrValueMapper, SpuAttrValue> implements SpuAttrValueService {

    @Autowired
    private SpuAttrValueMapper spuAttrValueMapper;

    @Override
    public List<SpuAttrValue> getBySpuAttrId(Long id) {

        return spuAttrValueMapper.selectList(new LambdaQueryWrapper<SpuAttrValue>().eq(SpuAttrValue::getSpuAttrId,id));
    }
}

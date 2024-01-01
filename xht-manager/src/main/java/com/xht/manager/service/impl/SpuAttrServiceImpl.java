package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xht.manager.mapper.SpuAttrMapper;
import com.xht.manager.service.SpuAttrService;
import com.xht.manager.service.SpuAttrValueService;
import com.xht.model.entity.product.SpuAttr;
import com.xht.model.entity.product.SpuAttrValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SpuAttrServiceImpl extends ServiceImpl<SpuAttrMapper, SpuAttr> implements SpuAttrService {
    @Autowired
    private SpuAttrMapper spuAttrMapper;

    @Autowired
    private SpuAttrValueService spuAttrValueService;

    @Override
    public List<SpuAttr> getAttrBySpu(Long spuId) {
        List<SpuAttr> spuAttrList = spuAttrMapper.selectList(new LambdaQueryWrapper<SpuAttr>().eq(SpuAttr::getSpuId, spuId));
        for (SpuAttr spuAttr : spuAttrList) {
           List<SpuAttrValue> attrValueList =  spuAttrValueService.getBySpuAttrId(spuAttr.getId());
           spuAttr.setAttrValueList(attrValueList);
        }
        return spuAttrList;
    }

    @Override
    @Transactional
    public void savaSpuAttr(List<SpuAttr> spuSaleAttrList, Long spuId) {
        for (SpuAttr spuAttr : spuSaleAttrList) {
             spuAttr.setSpuId(spuId);
             spuAttrMapper.insert(spuAttr);
            List<SpuAttrValue> attrValueList = spuAttr.getAttrValueList();
            for (SpuAttrValue spuAttrValue : attrValueList) {
                spuAttrValue.setSpuAttrId(spuAttr.getId());
            }
            spuAttrValueService.saveBatch(attrValueList);
        }
    }

}

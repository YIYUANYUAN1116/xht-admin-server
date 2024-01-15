package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.manager.mapper.SpuMapper;
import com.xht.manager.service.SpuAttrService;
import com.xht.manager.service.SpuAttrValueService;
import com.xht.manager.service.SpuImageService;
import com.xht.manager.service.SpuService;
import com.xht.model.dto.product.SpuDto;
import com.xht.model.entity.product.Spu;
import com.xht.model.entity.product.SpuAttr;
import com.xht.model.entity.product.SpuAttrValue;
import com.xht.model.entity.product.SpuImage;
import org.springframework.beans.BeanUtils;
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
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

    @Autowired
    SpuMapper spuMapper;

    @Autowired
    SpuImageService spuImageService;

    @Autowired
    SpuAttrService spuAttrService;

    @Autowired
    SpuAttrValueService spuAttrValueService;

    @Override
    public Page<Spu> listPage(Integer page, Integer limit,Long category3Id) {
        Page<Spu> spuPage = new Page<>(page, limit);
        LambdaQueryWrapper<Spu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Spu::getIsDeleted,0).eq(Spu::getCategory3Id,category3Id);

        return spuMapper.selectPage(spuPage, queryWrapper);
    }

    @Override
    @Transactional
    public void saveOrUpdateSpuDto(SpuDto spuDto) {
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuDto,spu);
//        spuMapper.insert(spu);
        this.saveOrUpdate(spu);

        //保存或更新img
        List<SpuImage> spuImageList = spuDto.getSpuImageList();
        if (spuImageList!=null && !spuImageList.isEmpty()){
            for (SpuImage spuImage : spuImageList) {
                spuImage.setSpuId(spu.getId());
            }
        }

        spuImageService.saveOrUpdateBatch(spuDto.getSpuImageList());


        //保存或更新attr
        List<SpuAttr> spuSaleAttrList = spuDto.getSpuSaleAttrList();
        if (spuSaleAttrList != null && !spuSaleAttrList.isEmpty()){
            for (SpuAttr spuAttr : spuSaleAttrList) {
                spuAttr.setSpuId(spu.getId());
                spuAttrService.saveOrUpdate(spuAttr);
                List<SpuAttrValue> attrValueList = spuAttr.getAttrValueList();
                if (attrValueList!=null && !attrValueList.isEmpty()){
                    for (SpuAttrValue spuAttrValue : attrValueList) {
                        spuAttrValue.setSpuAttrId(spuAttr.getId());
                        spuAttrValueService.saveOrUpdate(spuAttrValue);
                    }
                }
            }
        }

    }

}

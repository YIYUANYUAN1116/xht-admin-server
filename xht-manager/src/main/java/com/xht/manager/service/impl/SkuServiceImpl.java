package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.manager.mapper.SkuAttrMapper;
import com.xht.manager.service.*;
import com.xht.model.dto.product.AttrAndValueDto;
import com.xht.model.dto.product.SkuDto;
import com.xht.model.dto.product.SpuAttrAndValueDto;
import com.xht.model.entity.product.*;
import com.xht.manager.mapper.SkuMapper;
import com.xht.model.vo.product.SkuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    SkuAttrService skuAttrService;

    @Autowired
    SkuMapper skuMapper;

    @Autowired
    SkuImgService skuImgService;

    @Autowired
    CategoryAttrValueService categoryAttrValueService;

    @Autowired
    SpuAttrValueService spuAttrValueService;

    @Override
    @Transactional
    public void saveSkuDto(SkuDto skuDto) {
        Sku sku = new Sku();
        BeanUtils.copyProperties(skuDto,sku);
        this.saveOrUpdate(sku);

        List<AttrAndValueDto> skuAttrValueList = skuDto.getSkuAttrValueList();
        for (AttrAndValueDto attrAndValueDto : skuAttrValueList) {
            SkuAttr skuAttr = new SkuAttr();
            BeanUtils.copyProperties(attrAndValueDto,skuAttr);
            skuAttr.setSkuId(sku.getId());
            skuAttrService.saveOrUpdate(skuAttr);
        }

        List<SpuAttrAndValueDto> skuSaleAttrValueList = skuDto.getSkuSaleAttrValueList();
        for (SpuAttrAndValueDto spuAttrAndValueDto : skuSaleAttrValueList) {
            SkuAttr skuAttr = new SkuAttr();
            BeanUtils.copyProperties(spuAttrAndValueDto,skuAttr);
            skuAttr.setSkuId(sku.getId());
            skuAttrService.saveOrUpdate(skuAttr);
        }
    }

    @Override
    public List<SkuVo> findBySpuId(Long spuId) {
        List<Sku> skus = skuMapper.selectList(new LambdaQueryWrapper<Sku>().eq(Sku::getSpuId, spuId)
                .eq(Sku::getIsDeleted,0));

        return toSkuVo(skus);
    }

    @Override
    public Page<SkuVo> listAll(Integer page,Integer limit) {
        Page<Sku> skuPage = new Page<>(page, limit);
        LambdaQueryWrapper<Sku> queryWrapper = new LambdaQueryWrapper<>();
        Page<Sku> selectPage = skuMapper.selectPage(skuPage, queryWrapper);

        List<SkuVo> skuVo = toSkuVo(selectPage.getRecords());

        Page<SkuVo> result = new Page<>();
        result.setRecords(skuVo);
        result.setPages(selectPage.getPages());
        result.setMaxLimit(selectPage.maxLimit());
        result.setCurrent(selectPage.getCurrent());
        result.setSize(selectPage.getSize());
        result.setTotal(selectPage.getTotal());
        return result;
    }

    @Override
    public void onSale(Long skuId) {
        Sku sku = skuMapper.selectById(skuId);
        sku.setIsSale(true);
        skuMapper.updateById(sku);
    }

    @Override
    public void cancelSale(Long skuId) {
        Sku sku = skuMapper.selectById(skuId);
        sku.setIsSale(false);
        skuMapper.updateById(sku);
    }

    @Override
    public SkuVo getSkuInfo(Long skuId) {
        Sku sku = skuMapper.selectById(skuId);
        return toSkuVo(sku);
    }

    @Override
    public void deleteSku(Long skuId) {
        skuMapper.deleteById(skuId);
        skuAttrService.deleteBySkuId(skuId);
        skuImgService.deleteBySkuId(skuId);
    }


    public List<SkuVo> toSkuVo(List<Sku> skuList){
        List<SkuVo> skuVos = new ArrayList<>();
        for (Sku sku : skuList) {
            SkuVo skuVo = toSkuVo(sku);
            skuVos.add(skuVo);
        }
        return skuVos;
    }

    public SkuVo toSkuVo(Sku sku){
        SkuVo skuVo = new SkuVo();
        BeanUtils.copyProperties(sku,skuVo);
        List<SkuAttr> skuAttrList = skuAttrService.getBySkuId(sku.getId());
        List<AttrAndValueDto> skuAttrValueList= new ArrayList<>();
        List<SpuAttrAndValueDto> skuSaleAttrValueList = new ArrayList<>();
        for (SkuAttr skuAttr : skuAttrList) {
            if (skuAttr.getAttrId()!=null && skuAttr.getAttrValueId()!=null){
                AttrAndValueDto attrAndValueDto = new AttrAndValueDto();
                attrAndValueDto.setAttrId(skuAttr.getAttrId());
                attrAndValueDto.setAttrValueId(skuAttr.getAttrValueId());
                CategoryAttrValue byId = categoryAttrValueService.getById(skuAttr.getAttrValueId());
                if (byId!=null){
                    attrAndValueDto.setValueName(byId.getValueName());
                }
                skuAttrValueList.add(attrAndValueDto);
            }
            if (skuAttr.getSpuAttrId()!=null && skuAttr.getSpuAttrValueId()!=null){
                SpuAttrAndValueDto spuAttrAndValueDto = new SpuAttrAndValueDto();
                spuAttrAndValueDto.setSpuAttrId(skuAttr.getSpuAttrId());
                spuAttrAndValueDto.setSpuAttrValueId(skuAttr.getSpuAttrValueId());
                SpuAttrValue byId = spuAttrValueService.getById(skuAttr.getSpuAttrValueId());
                if (byId!=null){
                    spuAttrAndValueDto.setValueName(byId.getValueName());
                }
                skuSaleAttrValueList.add(spuAttrAndValueDto);
            }
        }
        skuVo.setSkuAttrValueList(skuAttrValueList);
        skuVo.setSkuSaleAttrValueList(skuSaleAttrValueList);
        List<SkuImg> imgList = skuImgService.getBySkuId(sku.getId());
        skuVo.setSkuImageList(imgList);
        return skuVo;
    }
}

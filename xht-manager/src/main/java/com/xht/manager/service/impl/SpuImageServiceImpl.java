package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.manager.mapper.SpuImageMapper;
import com.xht.manager.service.FileUploadService;
import com.xht.manager.service.SpuImageService;
import com.xht.model.entity.product.SpuImage;
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
public class SpuImageServiceImpl extends ServiceImpl<SpuImageMapper, SpuImage> implements SpuImageService {

    @Autowired
    private SpuImageMapper spuImageMapper;

    @Autowired
    private FileUploadService fileUploadService;

    @Override
    public List<SpuImage> getBySpuId(Long spuId) {
        return spuImageMapper.selectList(new LambdaQueryWrapper<SpuImage>().eq(SpuImage::getSpuId,spuId).eq(SpuImage::getIsDeleted,0));
    }

    @Override
    public void delete(SpuImage spuImage) {
        spuImageMapper.deleteById(spuImage);

        //删除minio
        fileUploadService.delete(spuImage.getImgUrl());
    }

}

package com.xht.manager.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.manager.mapper.BrandMapper;
import com.xht.manager.service.BrandService;
import com.xht.model.entity.product.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类品牌 服务实现类
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {

        return brandMapper.selectAll();
    }

    @Override
    public Page<Brand> findByPage(Integer page, Integer limit) {
        LambdaQueryWrapper<Brand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Brand::getIsDeleted,0);
        Page<Brand> brandPage = new Page<>(page, limit);
        return brandMapper.selectPage(brandPage, queryWrapper);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteById(id);
    }
}


package com.xht.manager.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.product.Brand;

import java.util.List;

/**
 * <p>
 * 分类品牌 服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
public interface BrandService extends IService<Brand> {
    List<Brand> findAll();

    Page<Brand> findByPage(Integer page, Integer limit);

    void delete(Integer id);
}

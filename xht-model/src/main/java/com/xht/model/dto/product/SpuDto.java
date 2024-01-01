package com.xht.model.dto.product;

import com.xht.model.entity.product.SpuAttr;
import com.xht.model.entity.product.SpuImage;
import lombok.Data;

import java.util.List;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/26  18:18
 */

@Data
public class SpuDto {
    private Long id;
    private Long category3Id;
    private String spuName;

    private String description;

    private Long tmId;

    List<SpuImage> spuImageList;

    List<SpuAttr> spuSaleAttrList;


}

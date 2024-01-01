package com.xht.model.vo.product;

import com.xht.model.dto.product.AttrAndValueDto;
import com.xht.model.dto.product.SpuAttrAndValueDto;
import com.xht.model.entity.product.SkuImg;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/27  20:50
 */
@Data
public class SkuVo {

    private Long id;

    private Long spuId;

    private BigDecimal price;

    private String skuName;

    private String skuDesc;

    private Double weight;

    private Long tmId;

    private Long category3Id;

    private String skuDefaultImg;

    private Boolean isSale;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

    List<AttrAndValueDto> skuAttrValueList;
    List<SpuAttrAndValueDto> skuSaleAttrValueList;

    List<SkuImg>  skuImageList;

}

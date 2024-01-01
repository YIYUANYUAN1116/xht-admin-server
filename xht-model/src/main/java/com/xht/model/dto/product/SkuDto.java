package com.xht.model.dto.product;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class SkuDto {

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

}

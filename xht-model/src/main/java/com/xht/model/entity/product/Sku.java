package com.xht.model.entity.product;

import com.xht.model.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author xht
 * @since 2023-12-27
 */
@Getter
@Setter
public class Sku extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Long spuId;

    private BigDecimal price;

    private String skuName;

    private String skuDesc;

    private Double weight;

    private Long tmId;

    private Long category3Id;

    private String skuDefaultImg;

    private Boolean isSale;

}

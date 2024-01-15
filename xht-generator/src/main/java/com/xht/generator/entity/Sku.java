package com.xht.generator.entity;

import com.xht.model.entity.base.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

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

    private Byte isSale;
}

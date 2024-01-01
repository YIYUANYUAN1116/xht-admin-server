package com.xht.generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.model.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author xht
 * @since 2023-12-26
 */
@Getter
@Setter
@TableName("spu_attr")
public class SpuAttr extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * spuid
     */
    private Integer spuId;

    /**
     * category_attr_id
     */
    private Integer baseSaleAttrId;

    /**
     * category_attr_value
     */
    private String saleAttrName;
}

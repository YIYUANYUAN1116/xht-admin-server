package com.xht.model.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.model.entity.base.BaseEntity;
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
@TableName("sku_attr")
public class SkuAttr extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * category_attr_id
     */
    private Long attrId;

    private Long spuAttrId;

    private Long attrValueId;

    private Long spuAttrValueId;
}

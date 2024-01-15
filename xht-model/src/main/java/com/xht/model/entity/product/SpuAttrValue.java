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
 * @since 2023-12-26
 */
@Getter
@Setter
@TableName("spu_attr_value")
public class SpuAttrValue extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 属性值
     */
    private String valueName;

    /**
     * attrId
     */
    private Long spuAttrId;

    /**
     * category_attr_id
     */
    private Long baseSaleAttrId;
}

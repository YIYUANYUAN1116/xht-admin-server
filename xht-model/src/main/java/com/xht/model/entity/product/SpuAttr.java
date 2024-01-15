package com.xht.model.entity.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.model.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private Long spuId;

    /**
     * category_attr_id
     */
    private Long baseSaleAttrId;

    /**
     * category_attr_value
     */
    private String saleAttrName;

    @TableField(exist = false)
    private List<SpuAttrValue> attrValueList;
}

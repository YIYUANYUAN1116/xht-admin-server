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
 * @since 2023-12-25
 */
@Getter
@Setter
@TableName("category_attr_value")
public class CategoryAttrValue extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 属性值
     */
    private String valueName;

    /**
     * attrId
     */
    private Long attrId;

    /**
     * 逻辑删除
     */
    private Byte isDelete;
}

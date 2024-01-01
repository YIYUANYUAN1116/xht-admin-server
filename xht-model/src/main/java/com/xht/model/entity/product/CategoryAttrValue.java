package com.xht.model.entity.product;

import com.xht.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
@Getter
@Setter
@Tag(name = "category_attr_value")
public class CategoryAttrValue  extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 属性值
     */
    private String valueName;

    /**
     * attrId
     */
    private Long attrId;

}

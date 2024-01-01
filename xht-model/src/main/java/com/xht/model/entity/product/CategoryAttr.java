package com.xht.model.entity.product;


import com.baomidou.mybatisplus.annotation.TableField;
import com.xht.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@Tag(name = "category_attr")
public class CategoryAttr extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性名称
     */
    private String attrName;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 分类级别
     */
    private Integer categoryLevel;



    @TableField(exist = false)
    List<CategoryAttrValue> attrValueList;
}

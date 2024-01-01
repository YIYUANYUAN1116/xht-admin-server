package com.xht.model.entity.product;

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
public class Spu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * spu名称
     */
    private String spuName;

    /**
     * spu描述
     */
    private String description;

    /**
     * 三级分类id
     */
    private Long category3Id;

    private Long tmId;
}

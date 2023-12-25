package com.xht.generator.entity;

import com.xht.model.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 分类品牌
 * </p>
 *
 * @author xht
 * @since 2023-12-25
 */
@Getter
@Setter
public class Brand extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌图标
     */
    private String logo;
}

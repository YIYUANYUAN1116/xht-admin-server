package com.xht.generator.entity;

import com.xht.model.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author xht
 * @since 2023-12-25
 */
@Getter
@Setter
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String name;

    private String imageUrl;

    /**
     * 父分类id
     */
    private Long parentId;

    /**
     * 是否显示[0-不显示，1显示]
     */
    private Byte status;

    /**
     * 排序
     */
    private Integer orderNum;
}

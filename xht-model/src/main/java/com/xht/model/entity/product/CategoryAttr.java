package com.xht.model.entity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@Tag(name = "category_attr")
public class CategoryAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

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

    /**
     * 逻辑删除
     */
    private Byte isDelete;
}

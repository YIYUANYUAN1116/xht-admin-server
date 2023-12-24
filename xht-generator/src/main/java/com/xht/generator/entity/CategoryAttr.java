package com.xht.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

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
@TableName("category_attr")
public class CategoryAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
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

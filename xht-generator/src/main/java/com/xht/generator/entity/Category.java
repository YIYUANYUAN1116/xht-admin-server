package com.xht.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author xht
 * @since 2023-12-24
 */
@Getter
@Setter
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    private Byte isDeleted;
}

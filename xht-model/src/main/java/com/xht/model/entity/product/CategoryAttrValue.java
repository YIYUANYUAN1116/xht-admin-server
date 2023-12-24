package com.xht.model.entity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("category_attr_value")
public class CategoryAttrValue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 属性值
     */
    private String valueName;

    /**
     * attrId
     */
    private Integer attrId;

    /**
     * 逻辑删除
     */
    private Byte isDelete;
}

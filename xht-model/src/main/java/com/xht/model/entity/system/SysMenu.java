package com.xht.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.model.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
@Getter
@Setter
@TableName("sys_menu")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 所属上级
     */
    private Long pId;

    /**
     * 菜单标题
     */
    private String name;

    private String code;

    /**
     * 状态(0:禁止,1:正常)
     */
    private Integer status;

    private Integer level;

    private String toCode;

    private Integer type;

    private Integer selected;

    @TableField(exist = false)
    private List<SysMenu> children;
}

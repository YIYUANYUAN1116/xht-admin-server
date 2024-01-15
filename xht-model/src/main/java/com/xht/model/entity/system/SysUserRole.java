package com.xht.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xht.model.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
@Getter
@Setter
@TableName("sys_user_role")
public class SysUserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 用户id
     */
    private Long userId;
}

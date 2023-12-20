package com.xht.model.entity.system;

import com.xht.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/19  21:10
 */

@Data
@Schema(description = "角色菜单实体类")
public class SysRoleMenu extends BaseEntity {
    @Schema(description = "角色id")
    private Long roleId;
    @Schema(description = "菜单id")
    private Long menuId;
    private Integer isHalf;
}

package com.xht.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xht.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> listByRoleId(@Param("roleId") Long roleId);
}

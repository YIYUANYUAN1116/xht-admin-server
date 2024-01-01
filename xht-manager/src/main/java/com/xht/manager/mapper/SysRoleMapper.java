package com.xht.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xht.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> selectByUserId(@Param("userId") Long userId);
}

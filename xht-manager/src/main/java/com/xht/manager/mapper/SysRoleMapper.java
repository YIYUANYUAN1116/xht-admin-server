package com.xht.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xht.model.dto.system.SysRoleDto;
import com.xht.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysRoleMapper extends BaseMapper<SysRole> {

    //角色列表的方法
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    //2 角色添加的方法
    void save(SysRole sysRole);

    //3 角色修改的方法
    void update(SysRole sysRole);

    //4 角色删除的方法
    void delete(Long roleId);

    //查询所有角色
    List<SysRole> findAll();

    List<SysRole> selectByUserId(@Param("userId") Long userId);
}

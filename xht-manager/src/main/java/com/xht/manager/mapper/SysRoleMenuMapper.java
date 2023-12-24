package com.xht.manager.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xht.model.entity.system.SysRoleMenu;
import com.xht.model.dto.system.AssginMenuDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    // 询角色分配过菜单id列表
    List<Long> findSysRoleMenuByRoleId(Long roleId);

    //删除角色分配菜单数据
    void deleteByRoleId(Long roleId);

    //保存分配数据
    void doAssign(AssginMenuDto assginMenuDto);

    //把父菜单isHalf半开状态 1
    void updateSysRoleMenuIsHalf(Long menuId);
}

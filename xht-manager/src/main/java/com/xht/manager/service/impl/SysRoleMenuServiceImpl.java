package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xht.manager.mapper.SysRoleMenuMapper;
import com.xht.manager.service.SysRoleMenuService;
import com.xht.model.entity.system.SysRoleMenu;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public void removeByRoleId(Long sysRoleId) {
        baseMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId,sysRoleId));
    }
}

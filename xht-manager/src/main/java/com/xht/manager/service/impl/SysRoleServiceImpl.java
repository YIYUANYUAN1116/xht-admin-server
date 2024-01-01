package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.manager.mapper.SysRoleMapper;
import com.xht.manager.mapper.SysRoleMenuMapper;
import com.xht.manager.service.SysMenuService;
import com.xht.manager.service.SysRoleMenuService;
import com.xht.manager.service.SysRoleService;
import com.xht.manager.utils.BuildTree;
import com.xht.model.entity.system.SysMenu;
import com.xht.model.entity.system.SysRole;
import com.xht.model.entity.system.SysRoleMenu;
import com.xht.model.vo.system.DoAssignMenuVo;
import com.xht.model.vo.system.ToAssignMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @Autowired
    SysMenuService sysMenuService;

    @Override
    public List<SysRole> getByUserId(Long userId) {
        return  baseMapper.selectByUserId(userId);
    }

    @Override
    public Page<SysRole> getAll(Integer page, Integer limit, String roleName) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(roleName)){
            queryWrapper.like(SysRole::getRoleName,roleName);
        }
        return baseMapper.selectPage(new Page<SysRole>(page,limit),queryWrapper);
    }

    @Override
    @Transactional
    public void removeByIdWithRe(Long sysRoleId) {
        sysRoleMenuService.removeByRoleId(sysRoleId);
        baseMapper.deleteById(sysRoleId);
    }

    @Override
    public ToAssignMenuVo toAssign(Long roleId) {
        ToAssignMenuVo toAssignMenuVo = new ToAssignMenuVo();
        List<SysMenu> list = sysMenuService.list();
        List<SysMenu> listByRoleId= sysMenuService.listByRoleId(roleId);
        List<Long> collect = listByRoleId.stream().map(SysMenu::getId).collect(Collectors.toList());
        for (SysMenu sysMenu : list) {
            if (collect.contains(sysMenu.getId())) sysMenu.setSelected(1);
        }
        toAssignMenuVo.setMenuArr(BuildTree.buildMenuTree(list));
        return toAssignMenuVo;
    }

    @Override
    public void doAssignRole(DoAssignMenuVo doAssignMenuVo) {
        List<Long> permissionIds = doAssignMenuVo.getPermissionIds();
        Long roleId = doAssignMenuVo.getRoleId();
        for (Long permissionId : permissionIds) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(permissionId);
            sysRoleMenuService.save(sysRoleMenu);
        }
    }


}

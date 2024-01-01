package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xht.manager.mapper.SysMenuMapper;
import com.xht.manager.service.SysMenuService;
import com.xht.model.entity.system.SysMenu;
import com.xht.service.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> listByRoleId(Long roleId) {
        return baseMapper.listByRoleId(roleId);
    }

    @Override
    public void removeId(Long id) {
        List<SysMenu> sysMenus = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getPId, id));
        if (!sysMenus.isEmpty()){
            throw new  CustomException("删除失败",999);
        }
    }
}

package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.manager.mapper.SysUserRoleMapper;
import com.xht.manager.service.SysUserRoleService;
import com.xht.model.entity.system.SysUserRole;
import com.xht.model.vo.system.DoAssignUserRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public void doAssignRole(DoAssignUserRole doAssignUserRole) {
        Long userId = doAssignUserRole.getUserId();
        List<Long> roleIdList = doAssignUserRole.getRoleIdList();
        baseMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId,userId));
        for (Long roleId : roleIdList) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleId);
            baseMapper.insert(sysUserRole);
        }
    }

    @Override
    public void removeByUserId(Long userId) {
        baseMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId,userId));
    }
}

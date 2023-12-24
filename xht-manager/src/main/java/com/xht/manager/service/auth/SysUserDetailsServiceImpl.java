package com.xht.manager.service.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xht.manager.config.SysUserDetails;
import com.xht.manager.mapper.SysRoleMapper;
import com.xht.manager.mapper.SysUserMapper;
import com.xht.manager.utils.RoleToAuthority;
import com.xht.model.entity.system.SysRole;
import com.xht.model.entity.system.SysUser;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/19  16:07
 */
@Service
public class SysUserDetailsServiceImpl implements UserDetailsService {
    private
    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, username));
        if (sysUser != null){
            List<SysRole> sysRoleList = sysRoleMapper.selectByUserId(sysUser.getId());
            return new SysUserDetails(sysUser, RoleToAuthority.toAuthority(sysRoleList));
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}

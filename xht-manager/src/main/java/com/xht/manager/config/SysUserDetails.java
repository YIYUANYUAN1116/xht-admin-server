package com.xht.manager.config;

import com.xht.model.entity.system.SysRole;
import com.xht.model.entity.system.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/19  11:13
 */
public class SysUserDetails implements UserDetails {
    private SysUser sysUser;

    // SpringSecurity对应的权限信息
    private List<SimpleGrantedAuthority> authorities;

    public SysUserDetails(SysUser sysUser,List<SimpleGrantedAuthority>  authorities) {
        this.sysUser = sysUser;
       this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //用户权限
       return this.authorities;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    public SysUser getSysUser(){
        return sysUser;
    }
    //账户未过期 默认true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //账户未锁定 默认true
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //账户认证未过期 默认true
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用
    @Override
    public boolean isEnabled() {
        return sysUser.getStatus().equals(1);
    }
}

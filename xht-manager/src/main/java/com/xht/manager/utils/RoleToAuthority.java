package com.xht.manager.utils;

import com.xht.model.entity.system.SysRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/22  10:07
 */
public class RoleToAuthority {

    public static List<SimpleGrantedAuthority> toAuthority(List<SysRole> permissionList){
        if (permissionList!=null){
         return permissionList.stream()
                    .filter(sysRole -> sysRole.getRoleName()!=null)
                    .map(sysRole -> new SimpleGrantedAuthority(sysRole.getRoleName()))
                    .collect(Collectors.toList());
        }
        return null;
    }
}

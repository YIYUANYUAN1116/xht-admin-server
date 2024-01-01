package com.xht.manager.utils;

import com.xht.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/29  15:48
 */
public class BuildTree {

    public static List<SysMenu> buildMenuTree(List<SysMenu> sysMenuList){
        List<SysMenu> parent = sysMenuList.stream().filter(sysMenu -> sysMenu.getPId() == 0).collect(Collectors.toList());
        for (SysMenu sysMenu : parent) {
            sysMenu.setChildren(findChildren(sysMenu,sysMenuList));
        }
        return parent;
    }

    private static List<SysMenu> findChildren(SysMenu sysMenuParent,List<SysMenu> sysMenuList){
        List<SysMenu> collect = sysMenuList.stream().filter(sysMenu -> Objects.equals(sysMenu.getPId(), sysMenuParent.getId())).collect(Collectors.toList());
        for (SysMenu sysMenu : collect) {
            sysMenu.setChildren(findChildren(sysMenu,sysMenuList));
        }
        return collect;
    }
}

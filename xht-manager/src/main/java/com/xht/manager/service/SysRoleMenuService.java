package com.xht.manager.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.system.SysRoleMenu;

/**
 * <p>
 * 角色菜单 服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    void removeByRoleId(Long sysRoleId);
}

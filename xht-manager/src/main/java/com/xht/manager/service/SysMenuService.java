package com.xht.manager.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.system.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> listByRoleId(Long roleId);

    void removeId(Long id);
}

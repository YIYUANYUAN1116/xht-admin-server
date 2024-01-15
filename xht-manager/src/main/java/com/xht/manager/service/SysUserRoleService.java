package com.xht.manager.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.system.SysUserRole;
import com.xht.model.vo.system.DoAssignUserRole;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    void doAssignRole(DoAssignUserRole doAssignRole);

    void removeByUserId(Long userId);
}

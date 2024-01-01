package com.xht.manager.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.system.SysRole;
import com.xht.model.vo.system.DoAssignMenuVo;
import com.xht.model.vo.system.ToAssignMenuVo;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-28
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> getByUserId(Long id);

    Page<SysRole> getAll(Integer page, Integer limit, String roleName);

    void removeByIdWithRe(Long sysRoleId);

    ToAssignMenuVo toAssign(Long roleId);

    void doAssignRole(DoAssignMenuVo doAssignMenuVo);
}

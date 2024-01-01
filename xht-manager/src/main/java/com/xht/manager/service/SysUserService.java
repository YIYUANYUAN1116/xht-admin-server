package com.xht.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.dto.system.LoginDto;
import com.xht.model.entity.system.SysUser;
import com.xht.model.vo.system.DoAssignUserRole;
import com.xht.model.vo.system.ToAssignUserVo;
import com.xht.model.vo.system.SysUserVo;

import java.util.List;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/18  20:07
 */
public interface SysUserService  extends IService<SysUser> {


    SysUser selectUserInfoByUserName(String username);

    String login(LoginDto loginDto);

    void logout(String token);

    Page<SysUserVo> getAll(Integer page, Integer limit,String userName);

    void saveOrUpdateVo(SysUserVo sysUserVo);

    ToAssignUserVo toAssign(Long userId);

    void doAssignRole(DoAssignUserRole doAssignRole);

    void removeByIdWithRe(Long userId);

    void removeBatchByIdsWithRe(List<Long> idlist);
}

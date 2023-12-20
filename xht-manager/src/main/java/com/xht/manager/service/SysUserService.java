package com.xht.manager.service;

import com.xht.model.dto.system.LoginDto;
import com.xht.model.entity.system.SysUser;
import com.xht.model.vo.system.LoginVo;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/18  20:07
 */
public interface SysUserService {


    SysUser selectUserInfoByUserName(String username);

    String login(String userName, String password);
}

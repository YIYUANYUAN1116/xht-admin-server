package com.xht.manager.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xht.model.dto.system.SysUserDto;
import com.xht.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface SysUserMapper extends BaseMapper<SysUser> {

    //根据用户名查询数据库表 sys_user表
    SysUser selectUserInfoByUserName(String userName);

    //1 用户条件分页查询接口
    List<SysUser> findByPage(SysUserDto sysUserDto);

    //2 用户添加
    void save(SysUser sysUser);

    //3 用户修改
    void update(SysUser sysUser);

    //4 用户删除
    void delete(Long userId);
}

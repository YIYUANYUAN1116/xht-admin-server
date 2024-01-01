package com.xht.model.vo.system;

import com.xht.model.entity.system.SysRole;
import lombok.Data;

import java.util.List;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/29  10:27
 */

@Data
public class ToAssignUserVo {
    List<SysRole> assignRoles;
    List<SysRole> allRolesList;
}

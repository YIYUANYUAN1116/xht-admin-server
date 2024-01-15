package com.xht.model.vo.system;

import lombok.Data;

import java.util.List;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/29  11:37
 */
@Data
public class DoAssignMenuVo {
    Long roleId;
    List<Long> permissionIds;
}

package com.xht.model.vo.system;

import lombok.Data;

import java.util.List;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/29  10:43
 */
@Data
public class DoAssignUserRole {
    List<Long> roleIdList;
    Long userId;
}

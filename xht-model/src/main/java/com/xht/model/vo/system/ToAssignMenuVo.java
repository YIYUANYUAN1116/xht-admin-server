package com.xht.model.vo.system;

import com.xht.model.entity.system.SysMenu;
import lombok.Data;

import java.awt.*;
import java.util.List;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/29  11:37
 */
@Data
public class ToAssignMenuVo {
    List<SysMenu> menuArr;
    List<SysMenu> selectArr;
}

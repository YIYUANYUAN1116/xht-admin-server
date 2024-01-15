package com.xht.model.vo.system;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xht.model.entity.system.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/28  15:15
 */
public class SysUserVo extends SysUser {
    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "昵称")
    private String name;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "图像")
    private String avatar;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "状态（1：正常 0：停用）")
    private Integer status;


    @Schema(description = "唯一标识")

    private Long id;


    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "是否删除")
    private Integer isDeleted;

    String roleName;
}

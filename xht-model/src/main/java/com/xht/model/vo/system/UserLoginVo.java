package com.xht.model.vo.system;

import com.xht.model.entity.system.SysUser;
import com.xht.model.vo.common.CaptChaVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : YIYUANYUAN
 * @description : 登录的vo
 * @date: 2023/12/20  21:08
 */
@Data
public class UserLoginVo {
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

    @Schema(description = "验证码key")
    private String codeKey ;        // 验证码的key

    @Schema(description = "验证码value")
    private String captcha ;      // 图片验证码对应的字符串数据

}

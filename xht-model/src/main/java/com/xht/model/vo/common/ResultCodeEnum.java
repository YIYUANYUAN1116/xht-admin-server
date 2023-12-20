package com.xht.model.vo.common;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(200 , "操作成功") ,
    LOGIN_ERROR(201 , "用户名或者密码错误"),
    VALIDATE_CODE_ERROR(202 , "验证码错误") ,
    LOGIN_AUTH(208 , "用户未登录"),
    USER_NAME_IS_EXISTS(209 , "用户名已经存在"),
    SYSTEM_ERROR(9999 , "您的网络有问题请稍后重试"),
    NODE_ERROR( 217, "该节点下有子节点，不可以删除"),
    DATA_ERROR(204, "数据异常"),
    ACCOUNT_STOP( 216, "账号已停用"),
    FORBIDDEN(403, "没有相关权限"),
    STOCK_LESS( 219, "库存不足"),
    LOGOUT_SUCCESS(200, "注销成功"),
    AUTHENTICATION_SUCCESS(200, "登录成功"),
    NOT_AUTHENTICATION(401, "未认证请求"),
    ACCESS_DENIED(403, "权限不足，访问被拒绝"),
    JWT_TOKEN_FORMAT_ERROR(403,"TOKEN异常");

    private Integer code ;      // 业务状态码
    private String message ;    // 响应消息

    private ResultCodeEnum(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }

}

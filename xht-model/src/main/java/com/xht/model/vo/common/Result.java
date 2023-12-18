package com.xht.model.vo.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Result<T> {
    //返回状态
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private T data;

    // 私有化构造
    private Result() {}

    // 返回数据
    public static <T> Result<T> build(T data, Integer code, String message) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    //通过枚举
    public static  <T> Result<T> build(T data,ResultCodeEnum resultCodeEnum){
        return build(data,resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }
}

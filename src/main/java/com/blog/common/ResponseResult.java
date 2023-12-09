package com.blog.common;

import com.blog.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.blog.enums.ResultCode.*;

@ApiModel(value = "统一返回结果类")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class ResponseResult<T> {
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "响应消息", required = false)
    private String message;

    /**
     * 响应码：参考`ResultCode`
     */
    @ApiModelProperty(value = "响应码", required = true)
    private Integer code;

    /**
     * 响应中的数据
     */
    @ApiModelProperty(value = "响应数据", required = false)
    private T data;

    /**
     * 是否成功
     */
    @ApiModelProperty(value = "是否成功", required = true)
    private Boolean flag;

    public static <T> ResponseResult<T> ok() {
        return resultVO(true, SUCCESS.getCode(), SUCCESS.getDesc(), null);
    }

    public static <T> ResponseResult<T> ok(T data) {
        return resultVO(true, SUCCESS.getCode(), SUCCESS.getDesc(), data);
    }

    public static <T> ResponseResult<T> ok(T data, String message) {
        return resultVO(true, SUCCESS.getCode(), message, data);
    }

    public static <T> ResponseResult<T> fail() {
        return resultVO(false, ERROR.getCode(), ERROR.getDesc(), null);
    }

    public static <T> ResponseResult<T> fail(ResultCode statusCodeEnum) {
        return resultVO(false, statusCodeEnum.getCode(), statusCodeEnum.getDesc(), null);
    }

    public static <T> ResponseResult<T> fail(String message) {
        return resultVO(false, message);
    }

    public static <T> ResponseResult<T> fail(T data) {
        return resultVO(false, ERROR.getCode(), ERROR.getDesc(), data);
    }

    public static <T> ResponseResult<T> fail(T data, String message) {
        return resultVO(false, ERROR.getCode(), message, data);
    }

    public static <T> ResponseResult<T> fail(Integer code, String message) {
        return resultVO(false, code, message, null);
    }

    private static <T> ResponseResult<T> resultVO(Boolean flag, String message) {
        return ResponseResult.<T>builder()
                .flag(flag)
                .code(flag ? SUCCESS.getCode() : ERROR.getCode())
                .message(message).build();
    }

    private static <T> ResponseResult<T> resultVO(Boolean flag, Integer code, String message, T data) {
        return ResponseResult.<T>builder()
                .flag(flag)
                .code(code)
                .message(message)
                .data(data).build();
    }
}

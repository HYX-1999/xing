package com.blog.exception;

import lombok.Data;

import com.blog.enums.ResultCode;
import lombok.EqualsAndHashCode;

import static com.blog.enums.ResultCode.ERROR;
import static com.blog.enums.ResultCode.ERROR_DEFAULT;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 6401507641198338287L;

    /** 异常代码 */
    protected Integer code;

    /** 异常消息 */
    protected String message;

    public BusinessException() {
        super();
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getDesc());
        this.code = resultCode.getCode();
        this.message = resultCode.getDesc();
    }

    public BusinessException(String msg) {
        super(msg);
        this.code = ERROR_DEFAULT.getCode();
        this.message = msg;
    }

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }

    public BusinessException(Integer code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.message = msg;
    }

    public BusinessException(Throwable cause) {
        super(cause);
        this.code = ERROR.getCode();
        this.message = cause.getMessage();
    }

    @Override
    public String toString() {
        return "errorCode: " + code + ", message: " + message;
    }

}

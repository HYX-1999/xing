package com.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.blog.enums.ResultCode;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private Integer code = ResultCode.FAILURE.getCode();

    private final String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(ResultCode rsultCode) {
        this.code = rsultCode.getCode();
        this.message = rsultCode.getDesc();
    }

}

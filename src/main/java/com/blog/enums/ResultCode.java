package com.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  <p> 响应码枚举 - 可参考HTTP状态码的语义 </p>
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    //成功
    SUCCESS( 200, "成功" ),
    //失败
    FAILURE( 400, "失败" ),


    // 系统级别错误码
    ERROR(-1, "操作异常"),
    ERROR_DEFAULT(500,"系统繁忙，请稍后重试"),
    NOT_LOGIN(401, "未登录或token已过期"),
    NO_PERMISSION(-7,"无权限"),
    ERROR_PASSWORD(-8,"用户帐号或者密码错误!"),
    DISABLE_ACCOUNT(-12,"该账号已被管理员禁止登录!"),


    // 服务层面
    ERROR_EXCEPTION_MOBILE_CODE(10003,"验证码不正确或已过期，请重新输入"),
    ERROR_USER_NOT_EXIST(10009, "用户不存在"),
    PARAMS_ILLEGAL(10018,"参数不合法!!"),
    CATEGORY_IS_EXIST(10019,"该分类名称已存在!"),
    CATEGORY_IS_TOP(10020,"该分类已经在顶端!!"),
    DATA_TAG_IS_EXIST(10021,"该数据标签已存在!"),
    CRAWLING_ARTICLE_FAILED(10022,"抓取文章失败!"),
    ARTICLE_NOT_EXIST(10023,"文章不存在!");

    public final Integer code;
    public final String desc;

}

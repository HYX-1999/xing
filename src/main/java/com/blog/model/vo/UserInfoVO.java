package com.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.blog.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoVO {

    /**
     * 用户账号id
     */
    private String id;

    /**
     * 用户信息id
     */
    private Integer userInfoId;

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 登录方式
     */
    private Integer loginType;


    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户简介
     */
    private String intro;

    /**
     * 个人网站
     */
    private String webSite;

    /**
     * 地址
     */
    private String address;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = DateUtils.FORMAT_STRING,timezone="GMT+8")
    private String registerTime;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = DateUtils.FORMAT_STRING,timezone="GMT+8")
    private String lastLoginTime;

    /**
     * 文章量
     */
    private int articleCount;

    /**
     * 收藏量
     */
    private int collectCount;

    /**
     * 笔记量
     */
    private int noteCount;

    /**
     * 评论量
     */
    private int commentCount;

    /**
     * 粉丝量
     */
    private int fansCount;


    /**
     * 粉丝量
     */
    private int watchCount;

    /**
     * 七天关注量
     */
    private int qiDayFollowedCount;

    /**
     * 背景封面
     */
    private String bjCover;

    /**
     * token
     */
    private String token;

}

package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 根据用户id获取用户信息
     * @param loginId 用户id
     */
    UserInfo getByUserId(Object loginId);
}

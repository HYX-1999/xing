package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.entity.User;
import com.blog.model.dto.SystemUserDTO;

public interface UserService extends IService<User> {

    ResponseResult<?> insertUser(SystemUserDTO userDto);
}

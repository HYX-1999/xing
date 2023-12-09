package com.blog.service;

import com.blog.common.ResponseResult;
import com.blog.model.dto.LoginDTO;

public interface LoginService {

    ResponseResult<String> login(LoginDTO loginDTO);
}

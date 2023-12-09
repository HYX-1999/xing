package com.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blog.annotation.OperationLogger;
import com.blog.common.ResponseResult;
import com.blog.model.dto.SystemUserDTO;
import com.blog.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/create")
    @SaCheckPermission("/user/create")
    @OperationLogger(value = "添加用户")
    @ApiOperation(value = "添加用户", httpMethod = "POST", response = ResponseResult.class, notes = "添加用户")
    public ResponseResult<?> insert(@RequestBody SystemUserDTO userDto) {
        return userService.insertUser(userDto);
    }
}

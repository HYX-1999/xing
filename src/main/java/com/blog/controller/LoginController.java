package com.blog.controller;

import com.blog.common.ResponseResult;
import com.blog.model.dto.LoginDTO;
import com.blog.service.LoginService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登录-接口")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseResult<String> login(@Validated @RequestBody LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }

}

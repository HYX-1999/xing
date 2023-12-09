package com.blog.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.blog.common.ResponseResult;
import com.blog.entity.User;
import com.blog.enums.ResultCode;
import com.blog.exception.BusinessException;
import com.blog.mapper.UserMapper;
import com.blog.model.dto.LoginDTO;
import com.blog.service.LoginService;
import com.blog.utils.AesEncryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.blog.constant.CommonConstant.CURRENT_USER;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginServiceImpl implements LoginService {

    private final UserMapper userMapper;

    @Override
    public ResponseResult<String> login(LoginDTO loginDTO) {

        User user = userMapper.selectNameAndPassword(loginDTO.getUsername(), AesEncryptUtils.aesEncrypt(loginDTO.getPassword()));
        if (user == null) {
            throw new BusinessException(ResultCode.ERROR_PASSWORD.desc);
        }
        if (loginDTO.getRememberMe()) {
            StpUtil.login(user.getId(),new SaLoginModel().setTimeout(60 * 60 * 24 * 7));
        }else {
            StpUtil.login(user.getId(),"system");
        }
        StpUtil.getSession().set(CURRENT_USER, userMapper.getById(user.getId()));
        return ResponseResult.ok(StpUtil.getTokenValue());
    }
}

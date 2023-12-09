package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.entity.User;
import com.blog.entity.UserInfo;
import com.blog.enums.LoginTypeEnum;
import com.blog.exception.BusinessException;
import com.blog.mapper.UserInfoMapper;
import com.blog.mapper.UserMapper;
import com.blog.model.dto.SystemUserDTO;
import com.blog.model.vo.UserInfoVO;
import com.blog.service.UserService;
import com.blog.utils.AesEncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<?> insertUser(SystemUserDTO userDto) {
        UserInfoVO userInfoVO = baseMapper.selectByUserName(userDto.getUsername());
        if (userInfoVO != null) {
            throw new BusinessException("用户名已存在");
        }
        UserInfo userInfo = UserInfo.builder().nickname(userDto.getNickname()).avatar(userDto.getAvatar()).build();
        userInfoMapper.insert(userInfo);

        User user = User.builder().username(userDto.getUsername()).password(AesEncryptUtils.aesEncrypt(userDto.getPassword())).status(userDto.getStatus()).userInfoId(userInfo.getId()).roleId(userDto.getRoleId()).loginType(LoginTypeEnum.EMAIL.getType()).build();
        baseMapper.insert(user);
        return ResponseResult.ok();
    }
}

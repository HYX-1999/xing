package com.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.blog.common.ResponseResult;
import com.blog.service.CloudOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Api(tags = "图片上传-接口")
@RequiredArgsConstructor
public class UploadController {

    private final CloudOssService cloudOssService;

    @SaCheckLogin
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ApiOperation(value = "上传图片",httpMethod = "POST", response = ResponseResult.class, notes = "上传图片")
    public ResponseResult<String> upload(MultipartFile multipartFile){
        return cloudOssService.upload(multipartFile);
    }

}

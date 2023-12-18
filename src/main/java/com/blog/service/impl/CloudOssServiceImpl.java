package com.blog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.blog.common.ResponseResult;
import com.blog.service.CloudOssService;
import com.blog.strategy.context.FileUploadStrategyContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CloudOssServiceImpl implements CloudOssService {

    private final FileUploadStrategyContext fileUploadStrategyContext;

    /**
     * 上传文件
     */
    @Override
    public ResponseResult<String> upload(MultipartFile file) {
        StpUtil.checkLogin();
        if (file.getSize() > 1024 * 1024 * 10) {
            return ResponseResult.fail("文件大小不能大于10M");
        }
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (!"jpg,jpeg,gif,png,mp4".toUpperCase().contains(suffix.toUpperCase())) {
            return ResponseResult.fail("请选择jpg,jpeg,gif,png,mp4格式的图片");
        }
        String key = fileUploadStrategyContext.executeFileUploadStrategy(file, suffix);
        return ResponseResult.ok(key);
    }
}

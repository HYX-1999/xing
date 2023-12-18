package com.blog.service;

import com.blog.common.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface CloudOssService {

    /**
     * 上传
     */
    ResponseResult<String> upload(MultipartFile file);

}

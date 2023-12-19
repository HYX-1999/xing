package com.blog.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传策略
 */
public interface UploadStrategy {

    String fileUpload(MultipartFile file, String path);

}

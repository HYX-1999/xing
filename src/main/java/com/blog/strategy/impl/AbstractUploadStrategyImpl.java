package com.blog.strategy.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.blog.config.properties.OssConfigProperties;
import com.blog.exception.BusinessException;
import com.blog.strategy.UploadStrategy;
import com.blog.utils.FileUtils;
import com.blog.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public abstract class AbstractUploadStrategyImpl implements UploadStrategy {

    @Override
    public String fileUpload(MultipartFile file, String path) {
        try {
            String md5 = FileUtils.getMd5(file.getInputStream());
            String extName = FileUtils.getExtName(file.getOriginalFilename());
            String fileName = md5 + extName;
            if (!exists(path + fileName)) {
                upload(path, fileName, file.getInputStream());
            }
            return getFileAccessUrl("/" + path + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("文件上传失败");
        }
    }

    public abstract Boolean exists(String filePath);

    public abstract void upload(String path, String fileName, InputStream inputStream) throws IOException;

    public abstract String getFileAccessUrl(String filePath);

}

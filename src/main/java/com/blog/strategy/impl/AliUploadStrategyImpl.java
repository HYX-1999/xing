package com.blog.strategy.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.blog.config.properties.OssConfigProperties;
import com.blog.strategy.FileUploadStrategy;
import com.blog.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service("aliUploadStrategyImpl")
@RequiredArgsConstructor
public class AliUploadStrategyImpl implements FileUploadStrategy {

    private final OssConfigProperties ossConfigProperties;

    @Override
    public String fileUpload(MultipartFile file, String suffix) {
        OSS ossClient = getOssClient();
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            String newFileName = UUIDUtils.getUuid() + "." + suffix;
            ossClient.putObject(ossConfigProperties.getBucketName(), newFileName, inputStream);
            return "https://" + ossConfigProperties.getBucketName() + "." + ossConfigProperties.getEndpoint() + "/" + newFileName;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private OSS getOssClient() {
        return new OSSClientBuilder().build(ossConfigProperties.getEndpoint(), ossConfigProperties.getAccessKeyId(), ossConfigProperties.getAccessKeySecret());
    }
}

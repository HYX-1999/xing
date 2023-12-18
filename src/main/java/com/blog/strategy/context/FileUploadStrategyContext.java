package com.blog.strategy.context;

import com.blog.strategy.FileUploadStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileUploadStrategyContext {

    @Value("${upload.mode}")
    private String uploadMode;

    private final Map<String, FileUploadStrategy> fileUploadStrategyMap;

    public String executeFileUploadStrategy(MultipartFile file, String suffix) {
        return fileUploadStrategyMap.get(uploadMode).fileUpload(file, suffix);
    }

}

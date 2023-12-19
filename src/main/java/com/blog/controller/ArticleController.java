package com.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.ResponseResult;
import com.blog.model.vo.SystemArticleListVO;
import com.blog.service.ArticleService;
import com.blog.strategy.context.UploadStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/article")
@Api(tags = "后台文章管理")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private UploadStrategyContext uploadStrategyContext;

    @SaCheckLogin
    @PostMapping(value = "/system/list")
    @ApiOperation(value = "文章列表", httpMethod = "POST", response = ResponseResult.class, notes = "文章列表")
    public ResponseResult<Page<SystemArticleListVO>> selectArticleList(@RequestBody Map<String, Object> map) {
        return articleService.selectArticleList(map);
    }

    @ApiOperation("上传文章图片")
    @ApiImplicitParam(name = "file", value = "文章图片", required = true, dataType = "MultipartFile")
    @PostMapping("/system/images")
    public ResponseResult<String> saveArticleImages(MultipartFile file) {
        return ResponseResult.ok(uploadStrategyContext.executeUploadStrategy(file, "article/"));
    }

}

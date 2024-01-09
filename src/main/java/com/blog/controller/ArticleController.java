package com.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.annotation.OperationLogger;
import com.blog.common.ResponseResult;
import com.blog.model.dto.ArticleDTO;
import com.blog.model.vo.SystemArticleListVO;
import com.blog.service.ArticleService;
import com.blog.strategy.context.UploadStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/system/info")
    @SaCheckPermission("/article/system/info")
    @ApiOperation(value = "文章详情", httpMethod = "GET", response = ResponseResult.class, notes = "文章详情")
    public ResponseResult<ArticleDTO> selectArticleById(Long id) {
        return articleService.selectArticleById(id);
    }

    @PostMapping(value = "/system/add")
    @SaCheckPermission("/article/system/add")
    @ApiOperation(value = "保存文章", httpMethod = "POST", response = ResponseResult.class, notes = "保存文章")
    @OperationLogger(value = "保存文章")
    public ResponseResult<?> insertArticle(@RequestBody ArticleDTO article) {
        return  articleService.insertArticle(article);
    }

    @PostMapping(value = "/system/update")
    @SaCheckPermission("/article/system/update")
    @ApiOperation(value = "修改文章", httpMethod = "POST", response = ResponseResult.class, notes = "修改文章")
    @OperationLogger(value = "修改文章")
    public ResponseResult<?> updateArticle(@RequestBody ArticleDTO article) {
        return articleService.updateArticle(article);
    }

    @ApiOperation("上传文章图片")
    @ApiImplicitParam(name = "file", value = "文章图片", required = true, dataType = "MultipartFile")
    @PostMapping("/system/images")
    public ResponseResult<String> saveArticleImages(MultipartFile file) {
        return ResponseResult.ok(uploadStrategyContext.executeUploadStrategy(file, "article/"));
    }

    @GetMapping(value = "/system/randomImg")
    @ApiOperation(value = "随机获取一张图片", httpMethod = "GET", response = ResponseResult.class, notes = "随机获取一张图片")
    public ResponseResult<?> randomImg() {
        return articleService.randomImg();
    }

}

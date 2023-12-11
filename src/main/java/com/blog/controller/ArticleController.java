package com.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.ResponseResult;
import com.blog.model.vo.SystemArticleListVO;
import com.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@Api(tags = "后台文章管理")
public class ArticleController {

    private final ArticleService articleService;

    @SaCheckLogin
    @PostMapping(value = "/system/list")
    @ApiOperation(value = "文章列表", httpMethod = "POST", response = ResponseResult.class, notes = "文章列表")
    public ResponseResult<Page<SystemArticleListVO>> selectArticleList(@RequestBody Map<String, Object> map) {
        return articleService.selectArticleList(map);
    }

}

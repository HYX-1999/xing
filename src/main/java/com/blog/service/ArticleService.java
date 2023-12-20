package com.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.entity.Article;
import com.blog.model.vo.SystemArticleListVO;

import java.util.Map;

public interface ArticleService extends IService<Article> {

    /**
     * 后台分页获取文章
     */
    ResponseResult<Page<SystemArticleListVO>> selectArticleList(Map<String,Object> map);

    /**
     * 随机获取图片
     */
    ResponseResult<?> randomImg();

}

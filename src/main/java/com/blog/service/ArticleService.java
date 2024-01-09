package com.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.entity.Article;
import com.blog.model.dto.ArticleDTO;
import com.blog.model.vo.SystemArticleListVO;

import java.util.Map;

public interface ArticleService extends IService<Article> {

    /**
     * 后台分页获取文章
     */
    ResponseResult<Page<SystemArticleListVO>> selectArticleList(Map<String,Object> map);

    /**
     * 添加文章
     */
    ResponseResult<?> insertArticle(ArticleDTO article);

    /**
     * 修改文章
     */
    ResponseResult<?> updateArticle(ArticleDTO article);

    /**
     * 后台根据主键获取文章详情
     */
    ResponseResult<ArticleDTO> selectArticleById(Long id);

    /**
     * 随机获取图片
     */
    ResponseResult<?> randomImg();

}

package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.entity.Article;
import com.blog.model.dto.ArticleDTO;
import com.blog.model.vo.SystemArticleListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 后台分页获取文章
     * @param page 分页对象
     * @param map 参数map
     */
    Page<SystemArticleListVO> selectArticle(@Param("page") Page<Object> page, @Param("param") Map<String,Object> map);

    /**
     * 后台根据主键获取文章详情
     */
    ArticleDTO selectArticlePrimaryKey(Long id);

}

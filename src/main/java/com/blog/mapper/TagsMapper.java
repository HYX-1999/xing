package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.entity.Tags;
import com.blog.model.vo.SystemTagListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsMapper extends BaseMapper<Tags> {

    /**
     * 添加标签
     */
    void saveArticleTags(@Param("articleId") Long articleId, @Param("tags")List<Long> tags);

    /**
     * 根据id删除文章对应中间表数据
     */
    void deleteByArticleIds(@Param("ids") List<Long> ids);

    /**
     * 分页获取标签
     */
    Page<SystemTagListVO> selectPageRecord(@Param("page") Page<Tags> objectPage, @Param("name") String name);

    List<Long> selectByArticleId(Long articleId);

    /**
     * 校验此标签是否存在文章关联
     */
    int validateTagIdIsExistArticle(Long id);
}

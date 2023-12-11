package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.entity.Article;
import com.blog.mapper.ArticleMapper;
import com.blog.mapper.UserMapper;
import com.blog.model.vo.SystemArticleListVO;
import com.blog.model.vo.SystemUserVO;
import com.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final UserMapper userMapper;

    @Override
    public ResponseResult<Page<SystemArticleListVO>> selectArticleList(Map<String, Object> map) {
        Page<SystemArticleListVO> data = baseMapper.selectArticle(new Page<>((Integer) map.get("current"), (Integer) map.get("size")), map);
        data.getRecords().forEach(item -> {
            SystemUserVO userInfo = userMapper.getById(item.getUserId());
            item.setNickname(userInfo.getNickname());
        });
        return ResponseResult.ok(data);
    }
}

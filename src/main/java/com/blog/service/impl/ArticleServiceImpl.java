package com.blog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.entity.Article;
import com.blog.enums.ResultCode;
import com.blog.exception.BusinessException;
import com.blog.mapper.ArticleMapper;
import com.blog.mapper.TagsMapper;
import com.blog.mapper.UserMapper;
import com.blog.model.dto.ArticleDTO;
import com.blog.model.vo.SystemArticleListVO;
import com.blog.model.vo.SystemUserVO;
import com.blog.service.ArticleService;
import com.blog.utils.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static com.blog.constant.CommonConstant.IMG_URL_API;

@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TagsMapper tagsMapper;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public ResponseResult<Page<SystemArticleListVO>> selectArticleList(Map<String, Object> map) {
        Page<SystemArticleListVO> data = baseMapper.selectArticle(new Page<>((Integer) map.get("current"), (Integer) map.get("size")), map);
        data.getRecords().forEach(item -> {
            SystemUserVO userInfo = userMapper.getById(item.getUserId());
            item.setNickname(userInfo.getNickname());
        });
        return ResponseResult.ok(data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<?> insertArticle(ArticleDTO article) {
        Article blogArticle = BeanCopyUtils.copyObject(article, Article.class);
        blogArticle.setUserId(StpUtil.getLoginIdAsString());
        int insert = baseMapper.insert(blogArticle);
        if (insert > 0) {
            tagsMapper.saveArticleTags(blogArticle.getId(), article.getTags());
        }
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> updateArticle(ArticleDTO article) {
        Article blogArticle = baseMapper.selectById(article.getId());
        if (ObjectUtil.isNull(blogArticle)) {
            throw new BusinessException(ResultCode.ARTICLE_NOT_EXIST);
        }
        String loginId = StpUtil.getLoginIdAsString();
        if (!blogArticle.getUserId().equals(loginId) && !StpUtil.hasRole("admin")) {
            throw new BusinessException(ResultCode.ERROR);
        }
        blogArticle = BeanCopyUtils.copyObject(article, Article.class);
        baseMapper.updateById(blogArticle);
        tagsMapper.deleteByArticleIds(Collections.singletonList(blogArticle.getId()));
        tagsMapper.saveArticleTags(blogArticle.getId(), article.getTags());
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<ArticleDTO> selectArticleById(Long id) {
        ArticleDTO articleDTO = baseMapper.selectArticlePrimaryKey(id);
        articleDTO.setTags(tagsMapper.selectByArticleId(id));
        return ResponseResult.ok(articleDTO);
    }

    @Override
    public ResponseResult<?> randomImg() {
        //文章封面图片 由https://api.btstu.cn/该网站随机获取
        String result = restTemplate.getForObject(IMG_URL_API, String.class);
        Object imgUrl = Objects.requireNonNull(JSON.parseObject(result)).get("imgurl");
        return ResponseResult.ok(imgUrl);
    }
}

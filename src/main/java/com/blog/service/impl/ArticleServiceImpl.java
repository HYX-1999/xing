package com.blog.service.impl;

import com.alibaba.fastjson2.JSON;
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
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

import static com.blog.constant.CommonConstant.IMG_URL_API;

@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private UserMapper userMapper;

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
    public ResponseResult<?> randomImg() {
        //文章封面图片 由https://api.btstu.cn/该网站随机获取
        String result = restTemplate.getForObject(IMG_URL_API, String.class);
        Object imgUrl = Objects.requireNonNull(JSON.parseObject(result)).get("imgurl");
        return ResponseResult.ok(imgUrl);
    }
}

package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.entity.Tags;
import com.blog.exception.BusinessException;
import com.blog.mapper.TagsMapper;
import com.blog.model.vo.SystemTagListVO;
import com.blog.service.TagsService;
import com.blog.utils.PageUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {

    @Override
    public ResponseResult<Page<SystemTagListVO>> listTags(String name) {
        Page<SystemTagListVO> list = baseMapper.selectPageRecord(new Page<>(PageUtils.getCurrent(), PageUtils.getSize()), name);
        return ResponseResult.ok(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<?> insertTag(Tags tags) {
        validateName(tags.getName());
        baseMapper.insert(tags);
        return ResponseResult.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<?> updateTag(Tags tags) {
        Tags entity = baseMapper.selectById(tags.getId());
        if (!entity.getName().equalsIgnoreCase(tags.getName())) {
            validateName(tags.getName());
        }
        baseMapper.updateById(tags);
        return ResponseResult.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<?> deleteById(Long id) {
        validateTagIdIsExistArticle(id);
        baseMapper.deleteById(id);
        return ResponseResult.ok();
    }

    private void validateTagIdIsExistArticle(Long id) {
        int count = baseMapper.validateTagIdIsExistArticle(id);
        if (count > 0){
            throw new BusinessException("标签下有文章，无法删除");
        }
    }

    private void validateName(String name) {
        Tags tagsName = baseMapper.selectOne(new QueryWrapper<Tags>().eq("name", name));
        if (ObjectUtils.isNotEmpty(tagsName)) {
            throw new BusinessException("标签名已存在");
        }
    }
}

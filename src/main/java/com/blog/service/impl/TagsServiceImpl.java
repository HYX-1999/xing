package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.entity.Tags;
import com.blog.mapper.TagsMapper;
import com.blog.model.vo.SystemTagListVO;
import com.blog.service.TagsService;
import com.blog.utils.PageUtils;
import org.springframework.stereotype.Service;

@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {

    @Override
    public ResponseResult<Page<SystemTagListVO>> listTags(String name) {
        Page<SystemTagListVO> list = baseMapper.selectPageRecord(new Page<>(PageUtils.getCurrent(), PageUtils.getSize()), name);
        return ResponseResult.ok(list);
    }
}

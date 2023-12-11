package com.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.entity.Tags;
import com.blog.model.vo.SystemTagListVO;

public interface TagsService extends IService<Tags> {

    ResponseResult<Page<SystemTagListVO>> listTags(String name);

}

package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.entity.Category;
import com.blog.mapper.CategoryMapper;
import com.blog.model.vo.SystemCategoryListVO;
import com.blog.service.CategoryService;
import com.blog.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public ResponseResult<Page<SystemCategoryListVO>> selectCategoryList(String name) {
        Page<SystemCategoryListVO> categoryPage = baseMapper.selectCategory(new Page<>(PageUtils.getCurrent(), PageUtils.getSize()), name);
        return ResponseResult.ok(categoryPage);
    }
}

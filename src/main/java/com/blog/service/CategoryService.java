package com.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.entity.Category;
import com.blog.model.vo.SystemCategoryListVO;

public interface CategoryService extends IService<Category> {

    ResponseResult<Page<SystemCategoryListVO>> selectCategoryList(String name);

}

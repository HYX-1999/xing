package com.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.ResponseResult;
import com.blog.entity.Category;
import com.blog.model.vo.SystemCategoryListVO;

public interface CategoryService extends IService<Category> {

    /**
     * 分类列表
     */
    ResponseResult<Page<SystemCategoryListVO>> selectCategoryList(String name);

    /**
     * 添加分类
     */
    ResponseResult<?> insertCategory(Category category);

    /**
     * 修改分类
     */
    ResponseResult<?> updateCategory(Category category);

    /**
     * 删除分类
     */
    ResponseResult<?> deleteCategory(Long id);

}

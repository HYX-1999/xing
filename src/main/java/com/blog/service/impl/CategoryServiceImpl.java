package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.ResponseResult;
import com.blog.entity.Article;
import com.blog.entity.Category;
import com.blog.exception.BusinessException;
import com.blog.mapper.ArticleMapper;
import com.blog.mapper.CategoryMapper;
import com.blog.model.vo.SystemCategoryListVO;
import com.blog.service.CategoryService;
import com.blog.utils.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.blog.enums.ResultCode.CATEGORY_IS_EXIST;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private ArticleMapper articleMapper;

    /**
     * 分类列表
     */
    @Override
    public ResponseResult<Page<SystemCategoryListVO>> selectCategoryList(String name) {
        Page<SystemCategoryListVO> categoryPage = baseMapper.selectCategory(new Page<>(PageUtils.getCurrent(), PageUtils.getSize()), name);
        return ResponseResult.ok(categoryPage);
    }

    /**
     * 添加分类
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<?> insertCategory(Category category) {
        Category name = baseMapper.selectOne(new QueryWrapper<Category>().eq("name", category.getName()));
        if (name != null) {
            throw new BusinessException(CATEGORY_IS_EXIST);
        }
        int rows = baseMapper.insert(category);
        return rows > 0 ? ResponseResult.ok() :ResponseResult.fail("添加分类失败");
    }

    /**
     * 修改分类
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<?> updateCategory(Category category) {
        Category name = baseMapper.selectOne(new QueryWrapper<Category>().eq("name", category.getName()));
        if (name != null && !name.getId().equals(category.getId())) {
            throw new BusinessException(CATEGORY_IS_EXIST);
        }
        int rows = baseMapper.updateById(category);
        return rows > 0 ? ResponseResult.ok() : ResponseResult.fail("修改分类失败");
    }

    /**
     * 删除分类
     */
    @Override
    public ResponseResult<?> deleteCategory(Long id) {
        Integer count = articleMapper.selectCount(new QueryWrapper<Article>().eq("category_id", id));
        if (count > 0) {
            throw new BusinessException( "分类下有文章，不能删除");
        }
        int rows = baseMapper.deleteById(id);
        return rows > 0 ? ResponseResult.ok(): ResponseResult.fail("删除分类失败");
    }
}

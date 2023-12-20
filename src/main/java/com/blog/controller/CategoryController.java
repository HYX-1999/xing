package com.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.annotation.OperationLogger;
import com.blog.common.ResponseResult;
import com.blog.entity.Category;
import com.blog.model.vo.SystemCategoryListVO;
import com.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
@Api(tags = "分类管理")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping(value = "/system/list",method = RequestMethod.GET)
    @SaCheckLogin
    @ApiOperation(value = "分类列表", httpMethod = "GET", response = ResponseResult.class, notes = "分类列表")
    public ResponseResult<Page<SystemCategoryListVO>> list(String name) { return categoryService.selectCategoryList(name); }

    @RequestMapping(value = "/system/add", method = RequestMethod.POST)
    @SaCheckPermission("/category/system/add")
    @ApiOperation(value = "新增分类", httpMethod = "POST", response = ResponseResult.class, notes = "新增分类")
    @OperationLogger(value = "新增分类")
    public ResponseResult<?> insertCategory(@RequestBody Category category) {
        return categoryService.insertCategory(category);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @SaCheckPermission("/system/category/update")
    @ApiOperation(value = "修改分类", httpMethod = "POST", response = ResponseResult.class, notes = "修改分类")
    @OperationLogger(value = "修改分类")
    public ResponseResult<?> update(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @SaCheckPermission("/system/category/delete")
    @ApiOperation(value = "删除分类", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除分类")
    @OperationLogger(value = "删除分类")
    public ResponseResult<?> deleteCategory(Long id){
        return categoryService.deleteCategory(id);
    }

}

package com.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.annotation.OperationLogger;
import com.blog.common.ResponseResult;
import com.blog.entity.Tags;
import com.blog.model.vo.SystemTagListVO;
import com.blog.service.TagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tags")
@Api(tags = "标签管理")
public class TagsController {

    @Resource
    private TagsService tagsService;

    @RequestMapping(value = "/system/list",method = RequestMethod.GET)
    @SaCheckLogin
    @ApiOperation(value = "标签列表", httpMethod = "GET", response = ResponseResult.class, notes = "标签列表")
    public ResponseResult<Page<SystemTagListVO>> list(String name){
        return tagsService.listTags(name);
    }

    @RequestMapping(value = "/system/add",method = RequestMethod.POST)
    @SaCheckPermission("/tags/system/add")
    @ApiOperation(value = "新增标签", httpMethod = "POST", response = ResponseResult.class, notes = "新增标签")
    @OperationLogger(value = "新增标签")
    public ResponseResult<?> insert(@RequestBody Tags tags) {
        return tagsService.insertTag(tags);
    }

    @RequestMapping(value = "/system/update",method = RequestMethod.POST)
    @SaCheckPermission("/tags/system/update")
    @ApiOperation(value = "修改标签", httpMethod = "POST", response = ResponseResult.class, notes = "修改标签")
    @OperationLogger(value = "修改标签")
    public ResponseResult<?> update(@RequestBody Tags tags){
        return tagsService.updateTag(tags);
    }

    @RequestMapping(value = "/system/remove",method = RequestMethod.DELETE)
    @SaCheckPermission("/tags/system/remove")
    @ApiOperation(value = "删除标签", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除标签")
    @OperationLogger(value = "删除标签")
    public ResponseResult<?> deleteById(Long  id){
        return tagsService.deleteById(id);
    }
}

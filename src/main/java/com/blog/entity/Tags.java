package com.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.blog.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("b_tags")
@ApiModel(value="Tags对象", description="博客标签表")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tags implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private int sort;

    @ApiModelProperty(value = "点击量")
    private int clickVolume;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = DateUtils.FORMAT_STRING,timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = DateUtils.FORMAT_STRING,timezone="GMT+8")
    private Date updateTime;


    public Tags(Long id, int clickVolume) {
        this.id = id;
        this.clickVolume = clickVolume;
    }

}

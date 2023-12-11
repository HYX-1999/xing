package com.blog.model.vo;

import com.blog.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemTagListVO {
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "封面图")
    private String avatar;

    @ApiModelProperty(value = "排序")
    private int sort;

    @ApiModelProperty(value = "点击量")
    private int clickVolume;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = DateUtils.FORMAT_STRING,timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "最后更新时间")
    @JsonFormat(pattern = DateUtils.FORMAT_STRING,timezone="GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "文章量")
    private int articleCount;
}

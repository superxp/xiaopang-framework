package com.xiaopang.core.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author necho.duan
 * @title: PageResult
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 11:40
 */
@ApiModel(
        description = "分页查询模型"
)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PageResult<T> {
    @ApiModelProperty("状态码")
    private long stateCode = 200L;
    @ApiModelProperty("状态消息")
    private String stateMsg = "OK";
    @ApiModelProperty("页码")
    private Long currentPage;
    @ApiModelProperty("分页大小")
    private Long pageSize;
    @ApiModelProperty("总页数")
    private Long totalPage;
    @ApiModelProperty("总数")
    private Long totalNum;
    @ApiModelProperty("数据集合")
    private List<T> data;
}
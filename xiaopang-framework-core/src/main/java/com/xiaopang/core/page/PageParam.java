package com.xiaopang.core.page;

/**
 * @author necho.duan
 * @title: PageParam
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 11:34
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.commons.lang3.math.NumberUtils;
/** @deprecated */
@ApiModel(
        description = "分页查询模型"
)
@Deprecated
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PageParam {
    @ApiModelProperty("页码")
    private Long currentPage = 1L;
    @ApiModelProperty("分页大小")
    private Long pageSize = 10L;

    public Long startOffset() {
        return NumberUtils.max(new long[]{0L, (this.currentPage - 1L) * this.pageSize});
    }



}


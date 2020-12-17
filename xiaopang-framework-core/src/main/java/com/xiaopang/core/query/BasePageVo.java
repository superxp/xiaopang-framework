package com.xiaopang.core.query;

/**
 * @author necho.duan
 * @title: BasePageVo
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 14:12
 */
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.math.NumberUtils;

public class BasePageVo extends BaseVo {
    @ApiModelProperty("页码")
    private Long currentPage = 1L;
    @ApiModelProperty("分页大小")
    private Long pageSize = 10L;

    public BasePageVo() {
    }

    public Long startOffset() {
        return NumberUtils.max(new long[]{0L, (this.currentPage - 1L) * this.pageSize});
    }

    public <T> Page<T> toPage() {
        return new Page(this.getCurrentPage(), this.getPageSize());
    }

    public Long getCurrentPage() {
        return this.currentPage;
    }

    public Long getPageSize() {
        return this.pageSize;
    }

    public void setCurrentPage(final Long currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(final Long pageSize) {
        this.pageSize = pageSize;
    }
}

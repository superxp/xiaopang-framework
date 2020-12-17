package com.xiaopang.core.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author necho.duan
 * @title: PageReqParam
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 11:36
 */
@ApiModel(
        description = "分页查询模型"
)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PageReqParam extends PageParam {
    @Nullable
    @ApiModelProperty("升序参数数组")
    private List<String> ascs;
    @Nullable
    @ApiModelProperty("降序参数数组")
    private List<String> descs;

}


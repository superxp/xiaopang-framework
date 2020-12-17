package com.xiaopang.core.query;

/**
 * @author necho.duan
 * @title: TenantIdVo
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 14:21
 */
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 租户id vo
 *
 * @author dream.lu
 */
public class TenantIdVo implements Serializable {

    @NotNull
    @ApiModelProperty("租户ID")
    private Long tenantId;
}


package com.xiaopang.core.query;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author necho.duan
 * @title: BaseVo
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 14:10
 */
public class BaseVo implements Serializable {
    @ApiModelProperty("开始时间")
    @JsonDeserialize(
            using = StartTimeDeserialize.class
    )
    private Date startTime;
    @ApiModelProperty("结束时间")
    @JsonDeserialize(
            using = EndTimeDeserialize.class
    )
    private Date endTime;

    public BaseVo() {
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setStartTime(final Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(final Date endTime) {
        this.endTime = endTime;
    }
}

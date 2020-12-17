package com.xiaopang.core.query;

/**
 * @author necho.duan
 * @title: IdStatusVo
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 14:16
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IdStatusVo<R extends Serializable, K extends Serializable> extends IdVo<R> {
    @NotNull
    private K status;

    public IdStatusVo(R id, K status) {
        this.id = id;
        this.status = status;
    }

    public IdStatusVo<R, K> of(R id, K status) {
        return new IdStatusVo<>(id, status);
    }
}

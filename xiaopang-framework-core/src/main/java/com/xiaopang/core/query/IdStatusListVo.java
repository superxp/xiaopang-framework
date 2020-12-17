package com.xiaopang.core.query;

/**
 * @author necho.duan
 * @title: IdStatusListVo
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 14:18
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IdStatusListVo<R extends Serializable, K extends Serializable> extends IdListVo<R> {

    @NotNull
    private K status;

    public IdStatusListVo(Set<R> list, K status) {
        this.idList = list;
        this.status = status;
    }

    public IdStatusListVo<R, K> of(Set<R> list, K status) {
        return new IdStatusListVo<>(list, status);
    }
}


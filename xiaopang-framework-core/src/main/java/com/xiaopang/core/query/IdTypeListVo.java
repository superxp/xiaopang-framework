package com.xiaopang.core.query;

/**
 * @author necho.duan
 * @title: IdTypeListVo
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 14:19
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
@EqualsAndHashCode(callSuper = false)
public class IdTypeListVo<R extends Serializable, K extends Serializable> extends IdListVo<R> {

    @NotNull
    private K type;

    public IdTypeListVo(Set<R> list, K type) {
        this.idList = list;
        this.type = type;
    }

    public IdTypeListVo<R, K> of(Set<R> list, K type) {
        return new IdTypeListVo<>(list, type);
    }
}


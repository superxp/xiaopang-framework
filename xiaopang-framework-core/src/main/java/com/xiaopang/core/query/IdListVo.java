package com.xiaopang.core.query;

/**
 * @author necho.duan
 * @title: IdListVo
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 14:19
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdListVo<T extends Serializable> implements Serializable {
    @NotEmpty
    protected Set<T> idList;

    public static <R extends Serializable> IdListVo<R> of(Set<R> list) {
        return new IdListVo<>(list);
    }
}

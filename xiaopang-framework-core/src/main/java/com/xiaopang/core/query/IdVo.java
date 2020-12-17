package com.xiaopang.core.query;

/**
 * @author necho.duan
 * @title: IdVo
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 14:17
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdVo<T extends Serializable> implements Serializable {
    @NotNull
    protected T id;

    public static <T extends Serializable> IdVo<T> of(T id) {
        return new IdVo<>(id);
    }
}


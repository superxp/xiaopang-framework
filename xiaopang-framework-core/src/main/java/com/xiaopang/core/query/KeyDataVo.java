package com.xiaopang.core.query;

/**
 * @author necho.duan
 * @title: KeyDataVo
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 14:21
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyDataVo<K extends Serializable, V extends Serializable> implements Serializable {
    private K key;
    private V value;

    public static <T extends Serializable, R extends Serializable> KeyDataVo<T, R> of(T key, R value) {
        return new KeyDataVo<>(key, value);
    }
}


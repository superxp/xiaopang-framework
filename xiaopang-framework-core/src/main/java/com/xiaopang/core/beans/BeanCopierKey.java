package com.xiaopang.core.beans;

/**
 * @author necho.duan
 * @title: BeanCopierKey
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 11:55
 */
public class BeanCopierKey {
    private final Class<?> source;
    private final Class<?> target;
    private final boolean useConverter;
    private final boolean nonNull;

    public Class<?> getSource() {
        return this.source;
    }

    public Class<?> getTarget() {
        return this.target;
    }

    public boolean isUseConverter() {
        return this.useConverter;
    }

    public boolean isNonNull() {
        return this.nonNull;
    }


    public BeanCopierKey(final Class<?> source, final Class<?> target, final boolean useConverter, final boolean nonNull) {
        this.source = source;
        this.target = target;
        this.useConverter = useConverter;
        this.nonNull = nonNull;
    }
}


package com.xiaopang.core.beans;

/**
 * @author necho.duan
 * @title: BeanCopyConverter
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 11:56
 */
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import  com.xiaopang.core.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Converter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

public class BeanCopyConverter implements Converter {
    private static final Logger log = LoggerFactory.getLogger(BeanCopyConverter.class);
    private static final ConcurrentMap<String, TypeDescriptor> TYPE_CACHE = new ConcurrentHashMap(64);
    private final Class<?> sourceClazz;
    private final Class<?> targetClazz;

    @Nullable
    @Override
    public Object convert(Object value, Class target, final Object fieldName) {
        if (value == null) {
            return null;
        } else if (UtilClass.isAssignableValue(target, value)) {
            return value;
        } else {
            try {
                TypeDescriptor sourceDescriptor = getTypeDescriptor(this.sourceClazz, (String)fieldName);
                TypeDescriptor targetDescriptor = getTypeDescriptor(this.targetClazz, (String)fieldName);
                return UtilConvert.convert(value, sourceDescriptor, targetDescriptor);
            } catch (Throwable var6) {
                log.warn("BeanCopyConverter error", var6);
                return null;
            }
        }
    }

    private static TypeDescriptor getTypeDescriptor(final Class<?> clazz, final String fieldName) {
        String srcCacheKey = clazz.getName() + fieldName;
        Try.UncheckedFunction<String, TypeDescriptor> uncheckedFunction = (key) -> {
            Field field = UtilReflect.getField(clazz, fieldName);
            if (field == null) {
                throw new NoSuchFieldException(fieldName);
            } else {
                return new TypeDescriptor(field);
            }
        };
        return (TypeDescriptor)TYPE_CACHE.computeIfAbsent(srcCacheKey, Try.of(uncheckedFunction));
    }

    public BeanCopyConverter(final Class<?> sourceClazz, final Class<?> targetClazz) {
        this.sourceClazz = sourceClazz;
        this.targetClazz = targetClazz;
    }
}
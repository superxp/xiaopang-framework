package com.xiaopang.core.utils;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.lang.Nullable;

/**
 * @author necho.duan
 * @title: UtilConvert
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 11:59
 */
public final class UtilConvert {
    @Nullable
    public static <T> T convert(@Nullable Object source, Class<T> targetType) {
        if (source == null) {
            return null;
        } else if (UtilClass.isAssignableValue(targetType, source)) {
            return  (T) source;
        } else {
            ConversionService conversionService = ApplicationConversionService.getSharedInstance();
            return conversionService.convert(source, targetType);
        }
    }

    @Nullable
    public static <T> T convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        } else {
            ConversionService conversionService = ApplicationConversionService.getSharedInstance();
            return  (T) conversionService.convert(source, sourceType, targetType);
        }
    }

    @Nullable
    public static <T> T convert(@Nullable Object source, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        } else {
            GenericConversionService conversionService = (GenericConversionService) ApplicationConversionService.getSharedInstance();
            return  (T) conversionService.convert(source, targetType);
        }
    }

    private UtilConvert() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

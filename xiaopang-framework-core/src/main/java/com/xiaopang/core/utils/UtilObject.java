package com.xiaopang.core.utils;

/**
 * @author necho.duan
 * @title: UtilObject
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 12:02
 */
import java.util.Optional;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

public final class UtilObject extends ObjectUtils {
    public static boolean isNull(@Nullable Object object) {
        return object == null;
    }

    public static boolean isNotNull(@Nullable Object object) {
        return object != null;
    }

    public static boolean isTrue(boolean bool) {
        return bool;
    }

    public static boolean isTrue(@Nullable Boolean bool) {
        return (Boolean)Optional.ofNullable(bool).orElse(Boolean.FALSE);
    }

    public static boolean isFalse(boolean bool) {
        return !isTrue(bool);
    }

    public static boolean isFalse(@Nullable Boolean bool) {
        return !isTrue(bool);
    }

    public static boolean isNotEmpty(@Nullable Object[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(@Nullable Object obj) {
        return !isEmpty(obj);
    }

    private UtilObject() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

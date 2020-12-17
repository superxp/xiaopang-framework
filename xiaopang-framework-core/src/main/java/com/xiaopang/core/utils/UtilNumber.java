package com.xiaopang.core.utils;

/**
 * @author necho.duan
 * @title: UtilNumber
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 12:10
 */
import org.springframework.lang.Nullable;
import org.springframework.util.NumberUtils;

public final class UtilNumber extends NumberUtils {
    static final byte[] DIGITS = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};

    public static int toInt(final String str) {
        return toInt(str, 0);
    }

    public static int toInt(@Nullable final String str, final int defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }

    public static long toLong(final String str) {
        return toLong(str, 0L);
    }

    public static long toLong(@Nullable final String str, final long defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException var4) {
                return defaultValue;
            }
        }
    }

    public static String to62String(long i) {
        int radix = DIGITS.length;
        byte[] buf = new byte[65];
        int charPos = 64;

        for(i = -i; i <= (long)(-radix); i /= (long)radix) {
            buf[charPos--] = DIGITS[(int)(-(i % (long)radix))];
        }

        buf[charPos] = DIGITS[(int)(-i)];
        return new String(buf, charPos, 65 - charPos, Charsets.UTF_8);
    }

    public static Integer getDefaultIfNull(Integer value, Integer defaultVal) {
        return null == value ? defaultVal : value;
    }

    public static Long getDefaultIfNull(Long value, Long defaultVal) {
        return null == value ? defaultVal : value;
    }

    private UtilNumber() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}


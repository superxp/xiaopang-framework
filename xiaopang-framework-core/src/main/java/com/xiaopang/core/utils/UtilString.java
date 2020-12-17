package com.xiaopang.core.utils;

/**
 * @author necho.duan
 * @title: UtilString
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 12:07
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

public final class UtilString extends StringUtils {
    private static final String INT_STR = "0123456789";
    private static final String STR_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String ALL_STR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String firstCharToLower(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] = (char)(arr[0] + 32);
            return new String(arr);
        } else {
            return str;
        }
    }

    public static String firstCharToUpper(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] = (char)(arr[0] - 32);
            return new String(arr);
        } else {
            return str;
        }
    }

    public static List<String> toUnderline(Collection<String> camelCaseList) {
        List<String> resultUnderlineList = new ArrayList();
        Iterator var2 = camelCaseList.iterator();

        while(var2.hasNext()) {
            String item = (String)var2.next();
            item = toUnderline(item);
            resultUnderlineList.add(item);
        }

        return resultUnderlineList;
    }

    public static String toUnderline(String stringWithCamelCase) {
        if (isEmpty(stringWithCamelCase)) {
            return "";
        } else {
            int len = stringWithCamelCase.length();
            StringBuilder sb = new StringBuilder(len);

            for(int i = 0; i < len; ++i) {
                char c = stringWithCamelCase.charAt(i);
                if (Character.isUpperCase(c) && i > 0) {
                    sb.append('_');
                }

                sb.append(Character.toLowerCase(c));
            }

            return sb.toString();
        }
    }

    public static String toCamelCase(String stringWithUnderline) {
        if (isEmpty(stringWithUnderline)) {
            return "";
        } else if (stringWithUnderline.indexOf(95) == -1) {
            return stringWithUnderline;
        } else {
            stringWithUnderline = stringWithUnderline.toLowerCase();
            char[] fromArray = stringWithUnderline.toCharArray();
            char[] toArray = new char[fromArray.length];
            int j = 0;

            for(int i = 0; i < fromArray.length; ++i) {
                if ('_' == fromArray[i]) {
                    ++i;
                    if (i < fromArray.length) {
                        toArray[j++] = Character.toUpperCase(fromArray[i]);
                    }
                } else {
                    toArray[j++] = fromArray[i];
                }
            }

            return new String(toArray, 0, j);
        }
    }

    public static boolean isBlank(@Nullable final CharSequence cs) {
        return !hasText(cs);
    }

    public static boolean isNotBlank(@Nullable final CharSequence cs) {
        return hasText(cs);
    }

    public static boolean isAnyBlank(final CharSequence... css) {
        return UtilObject.isEmpty(css) ? true : Stream.of(css).anyMatch(UtilString::isBlank);
    }

    public static boolean isAnyNotBlank(final CharSequence... css) {
        return UtilCollection.isEmpty(css) ? false : Arrays.stream(css).anyMatch((xva$0) -> {
            return isNoneBlank(xva$0);
        });
    }

    public static boolean isAnyNotBlank(Collection<String> css) {
        return UtilCollection.isEmpty(css) ? false : css.stream().anyMatch((xva$0) -> {
            return isNoneBlank(xva$0);
        });
    }

    public static boolean isNoneBlank(final CharSequence... css) {
        return UtilObject.isEmpty(css) ? false : Stream.of(css).allMatch(UtilString::isNotBlank);
    }

    public static boolean isNumeric(final CharSequence cs) {
        if (isBlank(cs)) {
            return false;
        } else {
            int i = cs.length();

            char chr;
            do {
                --i;
                if (i < 0) {
                    return true;
                }

                chr = cs.charAt(i);
            } while(chr >= '0' && chr <= '9');

            return false;
        }
    }

    public static String format(@Nullable String message, @Nullable Map<String, ?> params) {
        if (message == null) {
            return "";
        } else if (params != null && !params.isEmpty()) {
            StringBuilder sb = new StringBuilder((int)((double)message.length() * 1.5D));

            int cursor;
            int start;
            int end;
            for(cursor = 0; (start = message.indexOf("${", cursor)) != -1 && (end = message.indexOf(125, start)) != -1; cursor = end + 1) {
                sb.append(message, cursor, start);
                String key = message.substring(start + 2, end);
                Object value = params.get(trimWhitespace(key));
                sb.append(value == null ? "" : value);
            }

            sb.append(message.substring(cursor));
            return sb.toString();
        } else {
            return message;
        }
    }

    public static String format(@Nullable String message, @Nullable Object... arguments) {
        if (message == null) {
            return "";
        } else if (arguments != null && arguments.length != 0) {
            StringBuilder sb = new StringBuilder((int)((double)message.length() * 1.5D));
            int cursor = 0;
            int index = 0;

            int start;
            int end;
            for(int argsLength = arguments.length; (start = message.indexOf(123, cursor)) != -1 && (end = message.indexOf(125, start)) != -1 && index < argsLength; ++index) {
                sb.append(message, cursor, start);
                sb.append(arguments[index]);
                cursor = end + 1;
            }

            sb.append(message.substring(cursor));
            return sb.toString();
        } else {
            return message;
        }
    }

    public static String join(Collection<?> coll) {
        return collectionToCommaDelimitedString(coll);
    }

    public static String join(Collection<?> coll, String delim) {
        return collectionToDelimitedString(coll, delim);
    }

    public static String join(Object[] arr) {
        return arrayToCommaDelimitedString(arr);
    }

    public static String join(Object[] arr, String delim) {
        return arrayToDelimitedString(arr, delim);
    }

    public static String[] split(@Nullable String str, @Nullable String delimiter) {
        return delimitedListToStringArray(str, delimiter);
    }

    public static String[] splitTrim(@Nullable String str, @Nullable String delimiter) {
        return delimitedListToStringArray(str, delimiter, " \t\n\n\f");
    }

    public static boolean simpleMatch(@Nullable String pattern, @Nullable String str) {
        return PatternMatchUtils.simpleMatch(pattern, str);
    }

    public static boolean simpleMatch(@Nullable String[] patterns, String str) {
        return PatternMatchUtils.simpleMatch(patterns, str);
    }

    public static String getUUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long lsb = random.nextLong();
        long msb = random.nextLong();
        byte[] buf = new byte[32];
        formatUnsignedLong(lsb, buf, 20, 12);
        formatUnsignedLong(lsb >>> 48, buf, 16, 4);
        formatUnsignedLong(msb, buf, 12, 4);
        formatUnsignedLong(msb >>> 16, buf, 8, 4);
        formatUnsignedLong(msb >>> 32, buf, 0, 8);
        return new String(buf, Charsets.UTF_8);
    }

    private static void formatUnsignedLong(long val, byte[] buf, int offset, int len) {
        int charPos = offset + len;
        int radix = 16;
        int mask = radix - 1;

        do {
            --charPos;
            buf[charPos] = UtilNumber.DIGITS[(int)val & mask];
            val >>>= 4;
        } while(charPos > offset);

    }

    public static String random(int count) {
        return random(count, RandomType.ALL);
    }

    public static String random(int count, RandomType randomType) {
        if (count == 0) {
            return "";
        } else {
            Assert.isTrue(count > 0, "Requested random string length " + count + " is less than 0.");
            ThreadLocalRandom random = ThreadLocalRandom.current();
            char[] buffer = new char[count];

            for(int i = 0; i < count; ++i) {
                if (RandomType.INT == randomType) {
                    buffer[i] = "0123456789".charAt(random.nextInt("0123456789".length()));
                } else if (RandomType.STRING == randomType) {
                    buffer[i] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".length()));
                } else {
                    buffer[i] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt(random.nextInt("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".length()));
                }
            }

            return new String(buffer);
        }
    }

    public static String getDefaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }

    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        } else {
            int pads = size - str.length();
            if (pads <= 0) {
                return str;
            } else {
                return pads > 8192 ? leftPad(str, size, String.valueOf(padChar)) : repeat(padChar, pads).concat(str);
            }
        }
    }

    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        } else {
            if (isEmpty(padStr)) {
                padStr = " ";
            }

            int padLen = padStr.length();
            int strLen = str.length();
            int pads = size - strLen;
            if (pads <= 0) {
                return str;
            } else if (padLen == 1 && pads <= 8192) {
                return leftPad(str, size, padStr.charAt(0));
            } else if (pads == padLen) {
                return padStr.concat(str);
            } else if (pads < padLen) {
                return padStr.substring(0, pads).concat(str);
            } else {
                char[] padding = new char[pads];
                char[] padChars = padStr.toCharArray();

                for(int i = 0; i < pads; ++i) {
                    padding[i] = padChars[i % padLen];
                }

                return (new String(padding)).concat(str);
            }
        }
    }

    public static String repeat(char ch, int repeat) {
        if (repeat <= 0) {
            return "";
        } else {
            char[] buf = new char[repeat];

            for(int i = repeat - 1; i >= 0; --i) {
                buf[i] = ch;
            }

            return new String(buf);
        }
    }

    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        return org.apache.commons.lang3.StringUtils.equalsIgnoreCase(str1, str2);
    }

    private UtilString() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

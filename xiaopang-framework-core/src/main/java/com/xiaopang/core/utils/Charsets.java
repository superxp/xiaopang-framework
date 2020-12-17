package com.xiaopang.core.utils;

/**
 * @author necho.duan
 * @title: Charsets
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 12:09
 */
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class Charsets {
    public static final Charset ISO_8859_1;
    public static final String ISO_8859_1_NAME;
    public static final Charset GBK;
    public static final String GBK_NAME;
    public static final Charset UTF_8;
    public static final String UTF_8_NAME;

    private Charsets() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    static {
        ISO_8859_1 = StandardCharsets.ISO_8859_1;
        ISO_8859_1_NAME = ISO_8859_1.name();
        GBK = Charset.forName("GBK");
        GBK_NAME = GBK.name();
        UTF_8 = StandardCharsets.UTF_8;
        UTF_8_NAME = UTF_8.name();
    }
}

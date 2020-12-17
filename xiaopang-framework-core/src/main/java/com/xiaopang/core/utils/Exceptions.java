package com.xiaopang.core.utils;

import com.xiaopang.core.io.*;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author necho.duan
 * @title: Exceptions
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/2 19:21
 */
public final class Exceptions {

    public static RuntimeException unchecked(Throwable e) {
        if (e instanceof Error) {
            throw (Error)e;
        } else if (!(e instanceof IllegalAccessException) && !(e instanceof IllegalArgumentException) && !(e instanceof NoSuchMethodException)) {
            if (e instanceof InvocationTargetException) {
                return new RuntimeException(((InvocationTargetException)e).getTargetException());
            } else if (e instanceof RuntimeException) {
                return (RuntimeException)e;
            } else {
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }

                return new IllegalStateException(e);
            }
        } else {
            return new IllegalArgumentException(e);
        }
    }

    public static Throwable unwrap(Throwable wrapped) {
        Throwable unwrapped = wrapped;

        while(true) {
            while(!(unwrapped instanceof InvocationTargetException)) {
                if (!(unwrapped instanceof UndeclaredThrowableException)) {
                    return unwrapped;
                }

                unwrapped = ((UndeclaredThrowableException)unwrapped).getUndeclaredThrowable();
            }

            unwrapped = ((InvocationTargetException)unwrapped).getTargetException();
        }
    }

    public static String getStackTraceAsString(Throwable ex) {
        FastStringWriter stringWriter = new FastStringWriter(1024);
        ex.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private Exceptions() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }


}

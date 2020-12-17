package com.xiaopang.core.utils;

/**
 * @author necho.duan
 * @title: Try
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 12:03
 */
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.springframework.lang.Nullable;

public final class Try {
    public static <T, R> Function<T, R> of(Try.UncheckedFunction<T, R> mapper) {
        Objects.requireNonNull(mapper);
        return (t) -> {
            try {
                return mapper.apply(t);
            } catch (Throwable var3) {
                throw Exceptions.unchecked(var3);
            }
        };
    }

    public static <T> Consumer<T> of(Try.UncheckedConsumer<T> mapper) {
        Objects.requireNonNull(mapper);
        return (t) -> {
            try {
                mapper.accept(t);
            } catch (Throwable var3) {
                throw Exceptions.unchecked(var3);
            }
        };
    }

    public static <T> Supplier<T> of(Try.UncheckedSupplier<T> mapper) {
        Objects.requireNonNull(mapper);
        return () -> {
            try {
                return mapper.get();
            } catch (Throwable var2) {
                throw Exceptions.unchecked(var2);
            }
        };
    }

    public static Runnable of(Try.UncheckedRunnable runnable) {
        Objects.requireNonNull(runnable);
        return () -> {
            try {
                runnable.run();
            } catch (Throwable var2) {
                throw Exceptions.unchecked(var2);
            }
        };
    }

    public static <T> Callable<T> of(Try.UncheckedCallable<T> callable) {
        Objects.requireNonNull(callable);
        return () -> {
            try {
                return callable.call();
            } catch (Throwable var2) {
                throw Exceptions.unchecked(var2);
            }
        };
    }

    public static <T> Comparator<T> of(Try.UncheckedComparator<T> comparator) {
        Objects.requireNonNull(comparator);
        return (o1, o2) -> {
            try {
                return comparator.compare(o1, o2);
            } catch (Throwable var4) {
                throw Exceptions.unchecked(var4);
            }
        };
    }

    private Try() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @FunctionalInterface
    public interface UncheckedComparator<T> {
        int compare(T o1, T o2) throws Throwable;
    }

    @FunctionalInterface
    public interface UncheckedCallable<T> {
        @Nullable
        T call() throws Throwable;
    }

    @FunctionalInterface
    public interface UncheckedRunnable {
        void run() throws Throwable;
    }

    @FunctionalInterface
    public interface UncheckedSupplier<T> {
        @Nullable
        T get() throws Throwable;
    }

    @FunctionalInterface
    public interface UncheckedConsumer<T> {
        @Nullable
        void accept(@Nullable T t) throws Throwable;
    }

    @FunctionalInterface
    public interface UncheckedFunction<T, R> {
        @Nullable
        R apply(@Nullable T t) throws Throwable;
    }
}


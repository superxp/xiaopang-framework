package com.xiaopang.core.utils;

/**
 * @author necho.duan
 * @title: UtilCollection
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 11:43
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.*;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

public abstract class UtilCollection extends CollectionUtils {
    public UtilCollection() {
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static <T> boolean contains(@Nullable T[] array, final T element) {
        return array == null ? false : Arrays.stream(array).anyMatch((x) -> {
            return UtilObject.nullSafeEquals(x, element);
        });
    }

    public static String[] concat(String[] one, String[] other) {
        return (String[])concat(one, other, String.class);
    }

    public static <T> T[] concat(T[] one, T[] other, Class<T> clazz) {
        T[] target = (T[])Array.newInstance(clazz, one.length + other.length);
        System.arraycopy(one, 0, target, 0, one.length);
        System.arraycopy(other, 0, target, one.length, other.length);
        return target;
    }

    @SafeVarargs
    public static <E> Set<E> ofImmutableSet(E... es) {
        Objects.requireNonNull(es, "args es is null.");
        return (Set)Arrays.stream(es).collect(Collectors.toSet());
    }

    @SafeVarargs
    public static <E> List<E> ofImmutableList(E... es) {
        Objects.requireNonNull(es, "args es is null.");
        return (List)Arrays.stream(es).collect(Collectors.toList());
    }

    public static <T> List<List<T>> split(Collection<T> values, int size) {
        if (isEmpty(values)) {
            return new ArrayList(0);
        } else {
            List<List<T>> result = new ArrayList(values.size() / size + 1);
            List<T> tmp = new ArrayList(size);
            Iterator<T> var4 = values.iterator();

            while(var4.hasNext()) {
                T value = var4.next();
                tmp.add(value);
                if (tmp.size() >= size) {
                    result.add(tmp);
                    tmp = new ArrayList(size);
                }
            }

            if (!tmp.isEmpty()) {
                result.add(tmp);
            }

            return result;
        }
    }

    public static <T> List<Set<T>> splitToSet(Collection<T> values, int size) {
        if (isEmpty(values)) {
            return new ArrayList(0);
        } else {
            List<Set<T>> result = new ArrayList(values.size() / size + 1);
            Set<T> tmp = new HashSet(size);
            Iterator<T> var4 = (new LinkedHashSet(values)).iterator();

            while(var4.hasNext()) {
                T value = var4.next();
                tmp.add(value);
                if (tmp.size() >= size) {
                    result.add(tmp);
                    tmp = new HashSet(size);
                }
            }

            if (!tmp.isEmpty()) {
                result.add(tmp);
            }

            return result;
        }
    }

    public static <T> int size(Collection<T> collection) {
        return collection == null ? -1 : collection.size();
    }

    public static <T> int size(T[] arrays) {
        return arrays == null ? -1 : arrays.length;
    }

    public static <T> T[] toArray(Collection<T> collection) {
        Class<?> elClass = null;
        Iterator<T> var2 = collection.iterator();

        while(var2.hasNext()) {
            T el = var2.next();
            if (el != null) {
                elClass = el.getClass();
                break;
            }
        }

        if (elClass == null) {
            throw new IllegalArgumentException("collection=" + collection);
        } else {
            return toArray(collection, elClass);
        }
    }

    public static <T> T[] toArray(Collection<T> collection, Class<?> elClass) {
        return collection.toArray((T[])((Object[])Array.newInstance(elClass, collection.size())));
    }

    public static String[] toStringArrayDistinct(Collection<?> elements) {
        return toStrings(elements, new HashSet());
    }

    public static <T extends Collection<String>> String[] toStrings(Collection<?> elements, T collect) {
        Iterator var2 = elements.iterator();

        while(var2.hasNext()) {
            Object element = var2.next();
            collect.add(element == null ? null : element.toString());
        }

        return (String[])collect.toArray(new String[0]);
    }
}

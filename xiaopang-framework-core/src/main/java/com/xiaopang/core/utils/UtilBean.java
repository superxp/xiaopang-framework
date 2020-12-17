package com.xiaopang.core.utils;

/**
 * @author necho.duan
 * @title: UtilBean
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 11:46
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.xiaopang.core.beans.AbstractBeanCopier;
import com.xiaopang.core.beans.BeanCopyConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.Converter;
import org.springframework.lang.Nullable;

public final class UtilBean extends BeanUtils {
    public static <T> T newInstance(Class<?> clazz) {
        return (T)instantiateClass(clazz);
    }

    public static <T> T newInstance(String clazzStr) {
        ClassLoader classLoader = UtilClass.getDefaultClassLoader();

        try {
            Class<?> clazz = UtilClass.forName(clazzStr, classLoader);
            return newInstance(clazz);
        } catch (ClassNotFoundException var3) {
            throw new RuntimeException(var3);
        }
    }

    @Nullable
    public static Object getProperty(@Nullable Object bean, String propertyName) {
        if (bean == null) {
            return null;
        } else {
            BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(bean);
            return beanWrapper.getPropertyValue(propertyName);
        }
    }

    public static void setProperty(Object bean, String propertyName, Object value) {
        Objects.requireNonNull(bean, "bean Could not null");
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(bean);
        beanWrapper.setPropertyValue(propertyName, value);
    }

    @Nullable
    public static <T> T clone(@Nullable T source) {
        return source == null ? null : (T)copy(source, source.getClass());
    }

    @Nullable
    public static <T> T copy(@Nullable Object source, Class<T> clazz) {
        return source == null ? null : copy(source, source.getClass(), clazz);
    }

    @Nullable
    public static <T> T copy(@Nullable Object source, Class sourceClazz, Class<T> targetClazz) {
        if (source == null) {
            return null;
        } else {
            AbstractBeanCopier copier = AbstractBeanCopier.create(sourceClazz, targetClazz, false);
            T to = newInstance(targetClazz);
            copier.copy(source, to, (Converter)null);
            return to;
        }
    }

    public static <T> List<T> copy(@Nullable Collection<?> sourceList, Class<T> targetClazz) {
        if (sourceList != null && !sourceList.isEmpty()) {
            List<T> outList = new ArrayList(sourceList.size());
            Class<?> sourceClazz = null;
            Iterator var4 = sourceList.iterator();

            while(var4.hasNext()) {
                Object source = var4.next();
                if (source != null) {
                    if (sourceClazz == null) {
                        sourceClazz = source.getClass();
                    }

                    T bean = copy(source, sourceClazz, targetClazz);
                    outList.add(bean);
                }
            }

            return outList;
        } else {
            return Collections.emptyList();
        }
    }

    public static void copy(@Nullable Object source, @Nullable Object targetBean) {
        if (source != null && targetBean != null) {
            AbstractBeanCopier copier = AbstractBeanCopier.create(source.getClass(), targetBean.getClass(), false);
            copier.copy(source, targetBean, (Converter)null);
        }
    }

    public static void copyNonNull(@Nullable Object source, @Nullable Object targetBean) {
        if (source != null && targetBean != null) {
            AbstractBeanCopier copier = AbstractBeanCopier.create(source.getClass(), targetBean.getClass(), false, true);
            copier.copy(source, targetBean, (Converter)null);
        }
    }

    @Nullable
    public static <T> T copyWithConvert(@Nullable Object source, Class<T> targetClazz) {
        return source == null ? null : copyWithConvert(source, source.getClass(), targetClazz);
    }

    @Nullable
    public static <T> T copyWithConvert(@Nullable Object source, Class<?> sourceClazz, Class<T> targetClazz) {
        if (source == null) {
            return null;
        } else {
            AbstractBeanCopier copier = AbstractBeanCopier.create(sourceClazz, targetClazz, true);
            T to = newInstance(targetClazz);
            copier.copy(source, to, new BeanCopyConverter(sourceClazz, targetClazz));
            return to;
        }
    }

    public static <T> List<T> copyWithConvert(@Nullable Collection<?> sourceList, Class<T> targetClazz) {
        if (sourceList != null && !sourceList.isEmpty()) {
            List<T> outList = new ArrayList(sourceList.size());
            Class<?> sourceClazz = null;
            Iterator var4 = sourceList.iterator();

            while(var4.hasNext()) {
                Object source = var4.next();
                if (source != null) {
                    if (sourceClazz == null) {
                        sourceClazz = source.getClass();
                    }

                    T bean = copyWithConvert(source, sourceClazz, targetClazz);
                    outList.add(bean);
                }
            }

            return outList;
        } else {
            return Collections.emptyList();
        }
    }

    @Nullable
    public static <T> T copyProperties(@Nullable Object source, Class<T> targetClazz) throws BeansException {
        if (source == null) {
            return null;
        } else {
            T to = newInstance(targetClazz);
            copyProperties((Object)source, (Object)to);
            return to;
        }
    }

    public static <T> List<T> copyProperties(@Nullable Collection<?> sourceList, Class<T> targetClazz) throws BeansException {
        if (sourceList != null && !sourceList.isEmpty()) {
            List<T> outList = new ArrayList(sourceList.size());
            Iterator var3 = sourceList.iterator();

            while(var3.hasNext()) {
                Object source = var3.next();
                if (source != null) {
                    T bean = copyProperties(source, targetClazz);
                    outList.add(bean);
                }
            }

            return outList;
        } else {
            return Collections.emptyList();
        }
    }

    public static Map<String, Object> toMap(@Nullable Object bean) {
        return (Map)(bean == null ? new HashMap(0) : BeanMap.create(bean));
    }

    public static <T> T toBean(Map<String, Object> beanMap, Class<T> valueType) {
        Objects.requireNonNull(beanMap, "beanMap Could not null");
        T to = newInstance(valueType);
        if (beanMap.isEmpty()) {
            return to;
        } else {
            copy((Object)beanMap, (Object)to);
            return to;
        }
    }

    private UtilBean() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

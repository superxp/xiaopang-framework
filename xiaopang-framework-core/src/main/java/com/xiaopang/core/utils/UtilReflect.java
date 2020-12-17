package com.xiaopang.core.utils;

/**
 * @author necho.duan
 * @title: UtilReflect
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 12:05
 */
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.cglib.core.CodeGenerationException;
import org.springframework.core.convert.Property;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

public final class UtilReflect extends ReflectionUtils {
    public static PropertyDescriptor[] getBeanGetters(Class type) {
        return getPropertiesHelper(type, true, false);
    }

    public static PropertyDescriptor[] getBeanSetters(Class type) {
        return getPropertiesHelper(type, false, true);
    }

    public static PropertyDescriptor[] getPropertiesHelper(Class type, boolean read, boolean write) {
        try {
            PropertyDescriptor[] all = UtilBean.getPropertyDescriptors(type);
            if (read && write) {
                return all;
            } else {
                List<PropertyDescriptor> properties = new ArrayList(all.length);
                PropertyDescriptor[] var5 = all;
                int var6 = all.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    PropertyDescriptor pd = var5[var7];
                    if (read && pd.getReadMethod() != null || write && pd.getWriteMethod() != null) {
                        properties.add(pd);
                    }
                }

                return (PropertyDescriptor[])properties.toArray(new PropertyDescriptor[0]);
            }
        } catch (BeansException var9) {
            throw new CodeGenerationException(var9);
        }
    }

    @Nullable
    public static Property getProperty(Class<?> propertyType, String propertyName) {
        PropertyDescriptor propertyDescriptor = UtilBean.getPropertyDescriptor(propertyType, propertyName);
        return propertyDescriptor == null ? null : getProperty(propertyType, propertyDescriptor, propertyName);
    }

    public static Property getProperty(Class<?> propertyType, PropertyDescriptor propertyDescriptor, String propertyName) {
        Method readMethod = propertyDescriptor.getReadMethod();
        Method writeMethod = propertyDescriptor.getWriteMethod();
        return new Property(propertyType, readMethod, writeMethod, propertyName);
    }

    @Nullable
    public static TypeDescriptor getTypeDescriptor(Class<?> propertyType, String propertyName) {
        Property property = getProperty(propertyType, propertyName);
        return property == null ? null : new TypeDescriptor(property);
    }

    public static TypeDescriptor getTypeDescriptor(Class<?> propertyType, PropertyDescriptor propertyDescriptor, String propertyName) {
        Method readMethod = propertyDescriptor.getReadMethod();
        Method writeMethod = propertyDescriptor.getWriteMethod();
        Property property = new Property(propertyType, readMethod, writeMethod, propertyName);
        return new TypeDescriptor(property);
    }

    @Nullable
    public static Field getField(Class<?> clazz, String fieldName) {
        while(clazz != Object.class) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException var3) {
                clazz = clazz.getSuperclass();
            }
        }

        return null;
    }

    public static void setField(Field field, @Nullable Object target, @Nullable Object value) {
        makeAccessible(field);
        ReflectionUtils.setField(field, target, value);
    }

    @Nullable
    public static Object getField(Field field, @Nullable Object target) {
        makeAccessible(field);
        return ReflectionUtils.getField(field, target);
    }

    @Nullable
    public static Object invokeMethod(Method method, @Nullable Object target) {
        return invokeMethod(method, target);
    }

    @Nullable
    public static Object invokeMethod(Method method, @Nullable Object target, @Nullable Object... args) {
        makeAccessible(method);
        return ReflectionUtils.invokeMethod(method, target, args);
    }

    @Nullable
    public static <T extends Annotation> T getAnnotation(Class<?> clazz, String fieldName, Class<T> annotationClass) {
        Field field = getField(clazz, fieldName);
        return field == null ? null : field.getAnnotation(annotationClass);
    }

    public static Field[] getAllFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();

        for(Class superClass = clazz.getSuperclass(); superClass != null && superClass != Object.class; superClass = superClass.getSuperclass()) {
            Field[] superClassFields = superClass.getDeclaredFields();
            Field[] newFields = new Field[fields.length + superClassFields.length];
            System.arraycopy(fields, 0, newFields, 0, fields.length);
            System.arraycopy(superClassFields, 0, newFields, fields.length, superClassFields.length);
            fields = newFields;
        }

        return fields;
    }

    public static Object getFieldValue(Class<?> clazz, String fieldName) {
        PropertyDescriptor[] readPropertyDescriptors = getBeanGetters(clazz);
        PropertyDescriptor[] var3 = readPropertyDescriptors;
        int var4 = readPropertyDescriptors.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            PropertyDescriptor propertyDescriptor = var3[var5];
            if (propertyDescriptor.getDisplayName().equals(fieldName)) {
                return propertyDescriptor.getValue(fieldName);
            }
        }

        return null;
    }

    private UtilReflect() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}


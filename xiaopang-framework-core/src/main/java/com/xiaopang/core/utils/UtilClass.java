package com.xiaopang.core.utils;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author necho.duan
 * @title: UtilClass
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/2 19:53
 */
public final class UtilClass extends ClassUtils {
    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    public static MethodParameter getMethodParameter(Constructor<?> constructor, int parameterIndex) {
        MethodParameter methodParameter = new SynthesizingMethodParameter(constructor, parameterIndex);
        methodParameter.initParameterNameDiscovery(PARAMETER_NAME_DISCOVERER);
        return methodParameter;
    }

    public static MethodParameter getMethodParameter(Method method, int parameterIndex) {
        MethodParameter methodParameter = new SynthesizingMethodParameter(method, parameterIndex);
        methodParameter.initParameterNameDiscovery(PARAMETER_NAME_DISCOVERER);
        return methodParameter;
    }

    private UtilClass() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

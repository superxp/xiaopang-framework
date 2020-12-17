package com.xiaopang.core.beans;

import java.lang.annotation.*;

/**
 * @author necho.duan
 * @title: CopyProperty
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 12:06
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CopyProperty {
    String value() default "";

    boolean ignore() default false;
}

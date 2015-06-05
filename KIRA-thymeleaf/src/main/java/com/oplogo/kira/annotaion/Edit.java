package com.oplogo.kira.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yy on 5/8/14.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Edit {

    boolean enabled() default true;

    Type type() default Type.TEXT;
    enum Type{
        TEXT,
        TEXT_AREA,
        IMAGE,
        VIDEO,
        DATE
    }

    int rows() default 1;

    String css() default "";

    String hint() default "";
}

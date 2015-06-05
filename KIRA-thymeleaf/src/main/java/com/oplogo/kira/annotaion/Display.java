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
public @interface Display {

    String name() default "";

    boolean enabled() default true;

    Type type() default Type.TEXT;

    int rows() default 1;

    int scope() default Scope.LIST;

    enum Type {
        TEXT,
        HTML,
        IMAGE,
        VIDEO,
        DATE,
        CODE
    }

    class Scope {
        public static final int LIST = 0;
        public static final int DETAIL = 1;
    }
}

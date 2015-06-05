package com.oplogo.kira.annotation;

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

    int scope() default Scope.LIST;

    String href() default "";

    enum Type {
        TEXT,
        HTML,
        IMAGE,
        VIDEO,
        CODE,
        LINK,
        SET,
        REFERENCE,
        DATE
    }

    class Scope {
        public static final int LIST = 0;
        public static final int DETAIL = 1;
    }

    String cssStyle() default "";

    String cssClass() default "";

    String dateFormat() default "EEEE, MMM dd, yyyy HH:mm:ss a";

}

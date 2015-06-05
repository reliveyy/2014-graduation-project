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
public @interface Edit {

    boolean enabled() default true;

    Type type() default Type.TEXT;
    enum Type{
        TEXT,
        TEXT_AREA,
        IMAGE,
        VIDEO,
        DATE,
        REFERENCE,
        SET
    }

    int rows() default 1;

    String cssStyle() default "";

    String cssClass() default "";

    String hint() default "";

    String dateFormat() default "EEEE, MMM dd, yyyy HH:mm:ss a";

}

package com.oplogo.kira.util;

/**
 * Created by yy on 5/29/14.
 */
public class StringUtil {


    public static String splitCamelCase(String s, String delimiter) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                delimiter
        );
    }
}

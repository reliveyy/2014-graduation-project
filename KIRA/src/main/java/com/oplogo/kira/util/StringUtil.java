package com.oplogo.kira.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String grep(String regexPattern, String src) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(src);
        if (matcher.find()) return matcher.group(1);
        return null;
    }
}

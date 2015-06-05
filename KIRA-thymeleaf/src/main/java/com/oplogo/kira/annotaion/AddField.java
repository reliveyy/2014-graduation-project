package com.oplogo.kira.annotaion;

import com.oplogo.kira.util.RegexUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.lang.reflect.Field;

/**
 * Created by yy on 6/5/14.
 */
public class AddField {

    private String name;

    private String displayName;

    private Edit.Type type;

    private int rows;

    private String css;

    private String hint;

    public static AddField create(Field field) {
        AddField r = null;
        Edit edit = field.getAnnotation(Edit.class);
        Display display = field.getAnnotation(Display.class);
        if (display == null || display.enabled()) {
            if (edit == null) {
                return new AddField(field.getName()); // get a default AddField
            } else {
                if (edit.enabled()) {
                    r = new AddField();
                    r.name = field.getName();
                    r.displayName =
                            (display == null || display.name().isEmpty()) ?
                                    WordUtils.capitalize(r.name.replaceAll(RegexUtils.CAMEL_SPLIT, " "))
                                    : display.name();
                    r.type = edit.type();
                    r.rows = edit.rows();
                    r.css = edit.css();
                    r.hint = edit.hint();
                    return r;
                }
            }
        }
        return null;
    }

    private AddField() {

    }

    private AddField(String fieldName) {
        name = fieldName;
        displayName = WordUtils.capitalize(name.replaceAll(RegexUtils.CAMEL_SPLIT, " "));
        type = Edit.Type.TEXT;
        rows = 1;
        css = "";
        hint= "";
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Edit.Type getType() {
        return type;
    }

    public int getRows() {
        return rows;
    }

    public String getCss() {
        return css;
    }

    public String getHint() {
        return hint;
    }
}

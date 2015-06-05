package com.oplogo.kira.jsp.tag;

import com.oplogo.kira.annotaion.Display;
import com.oplogo.kira.util.RegexUtils;
import org.apache.commons.lang3.text.WordUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by yy on 6/2/14.
 */
public class DisplayNameTag extends TagSupport {

    private Field field;

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            Display display = field.getAnnotation(Display.class);
            if (display.name().isEmpty()) {
                out.println(WordUtils.capitalize(field.getName().replace(RegexUtils.CAMEL_SPLIT, " ")));
            } else {
                out.println(display.name());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}

package com.oplogo.kira.jsp.tag;

import com.oplogo.kira.annotation.Display;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by yy on 6/2/14.
 */
public class EditTag extends TagSupport {

    private Field field;
    private Object value;

    public void setField(Field field) {
        this.field = field;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            Display display = field.getAnnotation(Display.class);
            if(display.enabled()){
                if(display.scope() >= Display.Scope.LIST){
                    switch (display.type()){
                        case TEXT:
                            value = StringEscapeUtils.escapeHtml4(value.toString());
                            out.println(value);
                            break;

                        case HTML:
                            out.println(value);
                            break;

                        case IMAGE:
                            //TODO list image
                            break;

                        case VIDEO:
                            //TODO list video
                            break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}

package com.oplogo.kira.jsp.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by yy on 6/2/14.
 */
public class CodeTag extends TagSupport {

    protected String content;

    protected void out(String format, Object... args) {
        try {
            pageContext.getOut().println(String.format(format, args));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            StringReader sr = new StringReader(content);
            BufferedReader br = new BufferedReader(sr);
            String line;
            int ln = 1;
            out.println("<table class='k-code'>");
            while ((line = br.readLine()) != null) {
                out.print("<tr><td class='k-code-ln'>" + ln + "</td><td>");
                line = line.replace("\t", "&nbsp&nbsp&nbsp&nbsp");
                out.println(line);
                out.print("</td></tr>");
                ln++;
            }
            out.println("</table>");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

//    @Override
//    public int doEndTag() throws JspException {
//        return EVAL_PAGE;
//    }
//
//    @Override
//    public void release() {
//        super.release();
//    }


    public void setContent(String content) {
        this.content = content;
    }
}

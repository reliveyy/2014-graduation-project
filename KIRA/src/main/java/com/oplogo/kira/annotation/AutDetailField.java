package com.oplogo.kira.annotation;

import com.oplogo.kira.AutHibernateManager;
import com.oplogo.kira.model.AutManager;
import com.oplogo.kira.util.RegexUtils;
import com.oplogo.kira.util.StringUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Field;

/**
 * Created by yy on 6/5/14.
 */
public class AutDetailField {

    private String name;

    private String displayName;

    private Display.Type type;

    private Field field;

    private String href;

    private String cssStyle;

    private String cssClass;

    private String dateFormat;

    public static AutDetailField create(Field field) {
        AutDetailField r = null;
        Display display = field.getAnnotation(Display.class);
        if (display == null) {
            r = new AutDetailField(field);
        } else {
            if (display.enabled() && display.scope() <= Display.Scope.DETAIL) {
                r = new AutDetailField(field);
            }
        }
        return r;
    }

    private AutDetailField(Field field) {
        Display display = field.getAnnotation(Display.class);
        this.field = field;
        this.name = field.getName();
        if (display == null) {
            this.displayName = WordUtils.capitalize(name.replaceAll(RegexUtils.CAMEL_SPLIT, " "));
            switch (field.getType().getSimpleName()) {
                case "int":
                case "long":
                case "String":
                    this.type = Display.Type.TEXT;
                    break;
                case "Date":
                    this.type = Display.Type.DATE;
                    break;
                case "Set":
                    this.type = Display.Type.SET;
                    break;
                default:
                    this.type = Display.Type.REFERENCE;
            }
            this.href = "";
            this.cssClass = "";
            this.cssStyle = "";
            this.dateFormat = "EEEE, MMM dd, yyyy HH:mm:ss a";
        } else {
            this.displayName = (display.name().isEmpty()) ?
                    WordUtils.capitalize(name.replaceAll(RegexUtils.CAMEL_SPLIT, " "))
                    : display.name();
            this.type = display.type();
            this.href = display.href();
            this.cssClass = display.cssClass();
            this.cssStyle = display.cssStyle();
            this.dateFormat = display.dateFormat();
        }
    }

    AutHibernateManager autHibernateManager = AutHibernateManager.getInstance();

    AutManager autManager = AutManager.getInstance();

    public String html(Object value) {
        if (value == null) return "<i class='ban circle icon'></i>empty";
        StringBuilder sb = new StringBuilder();
        String result = "";
        switch (type) {
            case TEXT:
                result = StringEscapeUtils.escapeHtml4(value.toString());
                break;

            case HTML:
                result = value.toString();
                break;

            case DATE:
                result = value.toString();
                break;

            case LINK:
                result = String.format("<a href=\"%s\">%s</a>"
                        , href.replaceAll("\\{value\\}", value.toString())
                        , StringEscapeUtils.escapeHtml4(value.toString())
                );
                break;

            case CODE:
                sb
                        .append("<script src='/static/syntaxhighlighter_3.0.83/scripts/shCore.js'></script>")
                        .append("<script src='/static/syntaxhighlighter_3.0.83/scripts/shBrushJava.js'></script>")
                        .append("<link rel='stylesheet' href='/static/syntaxhighlighter_3.0.83/styles/shCoreDefault.css'/>")
                        .append("<script>SyntaxHighlighter.all();</script>")
                        .append("<pre class='brush:java'>")
                        .append(StringEscapeUtils.escapeHtml4(value.toString()))
                        .append("</pre>");
                result = sb.toString();
                break;

            case IMAGE:
                result = "[IMAGE]";
                break;

            case VIDEO:
                result = "[VIDEO]";
                break;

            case REFERENCE:
                String entityName = field.getType().getSimpleName();
                ExpressionParser parser = new SpelExpressionParser();
                EvaluationContext context = new StandardEvaluationContext(value);
                Expression exp;

                exp = parser.parseExpression("id");
                long itemId = (long) exp.getValue(context);

                String itemName;
                try {
                    exp = parser.parseExpression("name");
                    itemName = (String) exp.getValue(context);
                } catch (SpelEvaluationException e) {
                    itemName = "ID-" + itemId;
                }
                result = String.format("<a href='/auto/detail/%s/%d''>%s</a>"
                        , autManager.findByName(entityName).getUrlName()
                        , itemId
                        , itemName
                );
                break;

            case SET:
                entityName = StringUtil.grep(".(\\w+)>", field.getGenericType().toString());
                parser = new SpelExpressionParser();
                context = new StandardEvaluationContext(value);
                exp = parser.parseExpression("toArray()");
                Object[] valueArray = (Object[]) exp.getValue(context);
                result += "<ul>";
                for (int i = 0; i < valueArray.length; i++) {
                    parser = new SpelExpressionParser();
                    context = new StandardEvaluationContext(valueArray[i]);
                    exp = parser.parseExpression("id");
                    itemId = (long) exp.getValue(context);

                    try {
                        exp = parser.parseExpression("name");
                        itemName = (String) exp.getValue(context);
                    } catch (SpelEvaluationException e) {
                        itemName = "ID-" + itemId;
                    }
                    result += "<li>";
                    result += String.format("<a href='/auto/detail/%s/%d''>%s</a>"
                            , autManager.findByName(entityName).getUrlName()
                            , itemId
                            , itemName
                    );
                    result += "</li>";
                }
                result += "</ul>";

                break;

        }
        if (result.trim().isEmpty()) result = "<i class='ban circle icon'></i>empty";
        return result;
    }

//////////////////////////////////////////////////////////////////////////////////////////
//                                    getters
//////////////////////////////////////////////////////////////////////////////////////////


    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Display.Type getType() {
        return type;
    }

    public Field getField() {
        return field;
    }

    public String getHref() {
        return href;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public String getCssClass() {
        return cssClass;
    }

    public String getDateFormat() {
        return dateFormat;
    }
}

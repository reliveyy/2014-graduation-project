package com.oplogo.kira.annotation;

import com.oplogo.kira.AutHibernateManager;
import com.oplogo.kira.model.AutManager;
import com.oplogo.kira.util.RegexUtils;
import com.oplogo.kira.util.StringUtil;
import org.apache.commons.lang3.text.WordUtils;
import org.hibernate.Session;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by yy on 6/5/14.
 */
public class AutAddField {

    private String name;

    private String displayName;

    private Edit.Type type;

    private int rows;

    private String cssStyle;

    private String cssClass;

    private String hint;

    private Field field;

    private String dateFormat;

    AutHibernateManager autHibernateManager = AutHibernateManager.getInstance();

    AutManager autManager = AutManager.getInstance();

    private String inputHtml(String... params) {
        StringBuilder sb = new StringBuilder("<input");
        for (int i = 0; i < params.length; i += 2) {
            String attr = params[i];
            String value = params[i + 1];
            if (value != null && !value.trim().isEmpty())
                sb.append(" ").append(attr).append("='").append(value).append("'");
        }
        sb.append("/>");
        return sb.toString();
    }

    private String textAreaHtml(String... params) {
        StringBuilder sb = new StringBuilder("<textarea");
        for (int i = 0; i < params.length; i += 2) {
            String attr = params[i];
            String value = params[i + 1];
            if (value != null && !value.trim().isEmpty())
                sb.append(" ").append(attr).append("='").append(value).append("'");
        }
        sb.append("></textarea>");
        return sb.toString();
    }

    public String html() {
        StringBuilder sb = new StringBuilder();
        String result = "";
        switch (type) {
            case TEXT:
                result = inputHtml(
                        "id", name,
                        "name", name,
                        "style", cssStyle,
                        "class", cssClass,
                        "placeholder", hint,
                        "type", "text"
                );
                break;

            case TEXT_AREA:
                result = textAreaHtml(
                        "id", name,
                        "name", name,
                        "style", cssStyle,
                        "class", cssClass,
                        "placeholder", hint,
                        "rows", Integer.toString(rows)
                );
                break;

            case IMAGE:
                result = "[IMAGE FIELD]";
                break;

            case VIDEO:
                result = "[VIDEO FIELD]";
                break;

            case DATE:
                result = inputHtml(
                        "id", name,
                        "name", name,
                        "style", cssStyle,
                        "class", cssClass,
                        "placeholder", hint,
                        "type", "text"
                );
                break;

            case REFERENCE:
                Session session = autHibernateManager.getSession();
                session.beginTransaction();
                String entityName = field.getType().getSimpleName();
                List list = session.createQuery("from " + entityName).list();
                session.getTransaction().commit();
                result += "<div class='grouped inline fields'>";
                for (Object item : list) {
                    ExpressionParser parser = new SpelExpressionParser();
                    EvaluationContext context = new StandardEvaluationContext(item);
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

                    result += "<div class='field'><div class='ui radio checkbox'>";
                    result += inputHtml(
                            "type", "radio",
                            "name", name,
                            "value", Long.toString(itemId)
                    );
                    result += "<label>"
                            + String.format("<a href='/auto/detail/%s/%d'>%s</a>"
                            , autManager.findByName(entityName).getUrlName()
                            , itemId
                            , itemName)
                            + "</label>";
                    result += "</div></div>";
                }
                result += "</div>";
                break;

            case SET:
                session = autHibernateManager.getSession();
                session.beginTransaction();
                entityName = StringUtil.grep(".(\\w+)>", field.getGenericType().toString());
                list = session.createQuery("from " + entityName).list();
                session.getTransaction().commit();
                result += "<div class='grouped inline fields'>";
                for (Object item : list) {
                    ExpressionParser parser = new SpelExpressionParser();
                    EvaluationContext context = new StandardEvaluationContext(item);
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

                    result += "<div class='field'><div class='ui checkbox'>";
                    result += inputHtml(
                            "type", "checkbox",
                            "name", name,
                            "value", Long.toString(itemId)
                    );
                    result += "<label>"
                            + String.format("<a href='/auto/detail/%s/%d'>%s</a>"
                            , autManager.findByName(entityName).getUrlName()
                            , itemId
                            , itemName)
                            + "</label>";
                    result += "</div></div>";
                }
                result += "</div>";
                break;
        }
        if (result.trim().isEmpty()) result = "<i class='ban circle icon'></i>empty";
        return result;
    }


    public static AutAddField create(Field field) {
        AutAddField r = null;
        Edit edit = field.getAnnotation(Edit.class);
        Display display = field.getAnnotation(Display.class);
        if (display == null || display.enabled()) {
            if (edit == null) {
                return new AutAddField(field);
            } else {
                if (edit.enabled()) {
                    return new AutAddField(field);
                }
            }
        }
        return null;
    }

    private AutAddField(Field field) {
        Edit edit = field.getAnnotation(Edit.class);
        Display display = field.getAnnotation(Display.class);
        this.field = field;
        this.name = field.getName();
        this.displayName = (display == null || display.name().isEmpty()) ?
                WordUtils.capitalize(this.name.replaceAll(RegexUtils.CAMEL_SPLIT, " "))
                : display.name();
        if (edit == null) {
            switch (field.getType().getSimpleName()) {
                case "int":
                case "long":
                case "String":
                    this.type = Edit.Type.TEXT;
                    break;
                case "Date":
                    this.type = Edit.Type.DATE;
                    break;
                case "Set":
                    this.type = Edit.Type.SET;
                    break;
                default:
                    this.type = Edit.Type.REFERENCE;
            }
            this.rows = 1;
            this.cssStyle = "";
            this.cssClass = "";
            this.hint = "";
            this.dateFormat = "EEEE, MMM dd, yyyy HH:mm:ss a";
        } else {
            this.type = edit.type();
            this.rows = edit.rows();
            this.cssStyle = edit.cssStyle();
            this.cssClass = edit.cssClass();
            this.hint = edit.hint();
            this.dateFormat = edit.dateFormat();
        }
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

    public Edit.Type getType() {
        return type;
    }

    public int getRows() {
        return rows;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public String getCssClass() {
        return cssClass;
    }

    public String getHint() {
        return hint;
    }

    public Field getField() {
        return field;
    }

    public String getDateFormat() {
        return dateFormat;
    }
}

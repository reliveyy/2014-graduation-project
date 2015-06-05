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
public class AutEditField {

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
        String content = "";
        for (int i = 0; i < params.length; i += 2) {
            String attr = params[i];
            String value = params[i + 1];
            if (attr.equals("value")) {
                content = value;
                continue;
            }
            if (value != null && !value.trim().isEmpty())
                sb.append(" ").append(attr).append("='").append(value).append("'");
        }
        sb.append(">")
                .append(content)
                .append("</textarea>");
        return sb.toString();
    }

    public String html(Object value) {
        //if (value == null) return "";
        StringBuilder sb = new StringBuilder();
        String result = "";
        switch (type) {
            case TEXT:
                if (value == null) {
                    result = inputHtml(
                            "id", name,
                            "name", name,
                            "style", cssStyle,
                            "class", cssClass,
                            "placeholder", hint,
                            "type", "text"
                    );
                } else {
                    result = inputHtml(
                            "id", name,
                            "name", name,
                            "style", cssStyle,
                            "class", cssClass,
                            "placeholder", hint,
                            "type", "text",
                            "value", value.toString()
                    );
                }
                break;

            case TEXT_AREA:
                if (value == null) {
                    result = textAreaHtml(
                            "id", name,
                            "name", name,
                            "style", cssStyle,
                            "class", cssClass,
                            "placeholder", hint,
                            "rows", Integer.toString(rows)
                    );
                } else {
                    result = textAreaHtml(
                            "id", name,
                            "name", name,
                            "style", cssStyle,
                            "class", cssClass,
                            "placeholder", hint,
                            "rows", Integer.toString(rows),
                            "value", value.toString()
                    );
                }
                break;

            case IMAGE:
                result = "[IMAGE FIELD]";
                break;

            case VIDEO:
                result = "[VIDEO FIELD]";
                break;

            case DATE:
                if (value == null) {
                    result = inputHtml(
                            "id", name,
                            "name", name,
                            "style", cssStyle,
                            "class", cssClass,
                            "placeholder", hint,
                            "type", "text"
                    );
                } else {
                    result = inputHtml(
                            "id", name,
                            "name", name,
                            "style", cssStyle,
                            "class", cssClass,
                            "placeholder", hint,
                            "type", "text",
                            "value", value.toString()
                    );
                }
                break;

            case REFERENCE:
                ExpressionParser parser;
                EvaluationContext context;
                Expression exp;
                parser = new SpelExpressionParser();
                long valueId = -1;
                if (value != null) {
                    context = new StandardEvaluationContext(value);
                    exp = parser.parseExpression("id");
                    valueId = (long) exp.getValue(context);
                }

                Session session = autHibernateManager.getSession();
                session.beginTransaction();
                String entityName = field.getType().getSimpleName();
                List list = session.createQuery("from " + entityName).list();
                session.getTransaction().commit();
                result += "<div class='grouped inline fields'>";
                for (Object item : list) {
                    context = new StandardEvaluationContext(item);

                    exp = parser.parseExpression("id");
                    long itemId = (long) exp.getValue(context);

                    String itemName;
                    try {
                        exp = parser.parseExpression("name");
                        itemName = (String) exp.getValue(context);
                    } catch (SpelEvaluationException e) {
                        itemName = "id-" + itemId;
                    }

                    result += "<div class='field'><div class='ui radio checkbox'>";
                    if (itemId == valueId) {
                        result += inputHtml(
                                "type", "radio",
                                "name", name,
                                "value", Long.toString(itemId),
                                "checked", "checked"
                        );
                    } else {
                        result += inputHtml(
                                "type", "radio",
                                "name", name,
                                "value", Long.toString(itemId)
                        );
                    }
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
                Object[] valueArray = new Object[]{};

                if (value != null) {
                    parser = new SpelExpressionParser();
                    context = new StandardEvaluationContext(value);
                    exp = parser.parseExpression("toArray()");
                    valueArray = (Object[]) exp.getValue(context);
                }

                entityName = StringUtil.grep(".(\\w+)>", field.getGenericType().toString());
                list = session.createQuery("from " + entityName).list();
                session.getTransaction().commit();
                result += "<div class='grouped inline fields'>";
                for (Object item : list) {
                    parser = new SpelExpressionParser();
                    context = new StandardEvaluationContext(item);

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

                    boolean find = false;
                    for (int i = 0; i < valueArray.length; i++) {
                        context = new StandardEvaluationContext(valueArray[i]);
                        exp = parser.parseExpression("id");
                        long tmpId = (long) exp.getValue(context);
                        if (itemId == tmpId) {
                            find = true;
                            break;
                        }
                    }
                    if (find) {
                        result += inputHtml(
                                "type", "checkbox",
                                "name", name,
                                "value", Long.toString(itemId),
                                "checked", "checked"
                        );
                    } else {
                        result += inputHtml(
                                "type", "checkbox",
                                "name", name,
                                "value", Long.toString(itemId)
                        );
                    }
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

    public static AutEditField create(Field field) {
        Edit edit = field.getAnnotation(Edit.class);
        Display display = field.getAnnotation(Display.class);
        if (display == null || display.enabled()) {
            if (edit == null) {
                return new AutEditField(field);
            } else {
                if (edit.enabled()) {
                    return new AutEditField(field);
                }
            }
        }
        return null;
    }

    private AutEditField(Field field) {
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

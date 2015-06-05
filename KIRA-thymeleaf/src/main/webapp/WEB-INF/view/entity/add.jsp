<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 5/19/14
  Time: 9:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        .error {
            color: red;
        }

        #container {
            padding: 16px;
        }
    </style>
</head>
<body>
<div class="ui menu" style="border-radius: 0;margin-top: 0px;">
    <a class="item" href="/entity/list">
        <i class="left icon"></i> Back to list
    </a>
</div>
<div id="container">
    <h2 class="ui header">
        New Entity
    </h2>
    <form:form cssClass="ui form segment">
        <c:forEach var="field" items="${fields}">
            <div class="field">
                <label>${field.displayName}</label>
                <c:choose>
                    <c:when test="${field.type == 'TEXT'}">
                        <div>
                            <form:input path="${field.name}" cssStyle="${field.css}" placeholder="${field.hint}"/>
                            <form:errors path="${field.name}" cssClass="error"/>
                        </div>
                    </c:when>
                    <c:when test="${field.type == 'TEXT_AREA'}">
                        <div>
                            <form:textarea path="${field.name}" cssStyle="${field.css}" rows="${field.rows}"
                                           placeholder="${field.hint}"/>
                            <form:errors path="${field.name}" cssClass="error"/>
                        </div>
                    </c:when>
                </c:choose>

            </div>
        </c:forEach>
        <input type="submit" value="Submit" class="ui blue submit button"/>
    </form:form>
</div>
</body>
</html>

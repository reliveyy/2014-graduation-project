<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 5/19/14
  Time: 9:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld" %>
<html>
<head>
    <link rel="stylesheet" href="/static/semantic-ui/css/semantic.min.css"/>
    <link rel="stylesheet/less" href="/static/css/home.less"/>
    <script src="/static/js/jquery-2.1.0.min.js"></script>
    <script src="/static/js/less-1.7.0.min.js"></script>
    <script src="/static/semantic-ui/javascript/semantic.min.js"></script>
    <style>
        #container {
            padding: 16px;
        }
    </style>
</head>
<body>
<div class="ui menu" style="border-radius: 0;margin-top: 0px;">
    <a class="item" href="/auto/${aut.urlName}">
        <i class="left icon"></i> Back to list
    </a>
    <a class="item" href="/auto/edit/${aut.urlName}/${id}">
        <i class="edit icon"></i> Edit
    </a>
</div>
<div id="container">
    <h2 class="ui header">
        ${aut.name} Detail
    </h2>
    <div class="ui segment">
        <div class="ui very relaxed divided list">
            <c:forEach var="field" items="${fields}">
                <div class="item">
                    <div class="content">
                        <div class="header">
                                ${field.displayName}
                        </div>
                        <div class="description">
                                ${field.html(command[field.name])}
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>

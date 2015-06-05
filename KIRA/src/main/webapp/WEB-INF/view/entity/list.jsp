<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 5/19/14
  Time: 9:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script>
    </script>
</head>
<body>
<div class="ui menu" style="border-radius: 0;margin-top: 0px;">
    <a class=" red item" href="/entity/add">
        <i class="add icon"></i> New Entity
    </a>
</div>
<div id="container">
    <h2 class="ui header">
        Entity List
    </h2>
    <c:choose>
        <c:when test="${(list == null)||list.size()==0}">
            <div class="ui warning message">
                <div class="header">
                    Sorry
                </div>
                No result.
            </div>
        </c:when>
        <c:otherwise>
            <table class="ui table segment">
                <thead>
                <tr>
                    <c:forEach var="field" items="${fields}">
                        <th>${field.displayName}</th>
                    </c:forEach>
                    <th>
                        Operations
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${list}">
                    <tr>
                        <c:forEach var="field" items="${fields}">
                            <td>${field.html(item[field.name])}</td>
                        </c:forEach>
                        <c:set var="id" value="${item.id}"/>
                        <td>
                            <a href="/entity/edit/${id}">Edit</a> |
                            <a href="/entity/detail/${id}">Detail</a> |
                            <a href="/entity/delete/${id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

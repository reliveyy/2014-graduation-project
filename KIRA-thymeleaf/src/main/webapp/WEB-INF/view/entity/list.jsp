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
    <style>
        #container {
            padding: 16px;
        }
    </style>
    <script>
    </script>
</head>
<body>
<div id="container">
    <h2 class="ui header">
        Entity List
    </h2>

    <a class="ui blue button" href="/entity/add">New Entity</a>

    <c:choose>
        <c:when test="${list == null}">
            <div class="ui warning message">
                <i class="close icon"></i>

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
                        <th>${field}</th>
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
                            <td>${item[field]}</td>
                        </c:forEach>
                        <c:set var="id" value="${item.id}"/>
                        <td>
                            <a href="/entity/edit/${id}">Edit</a>|
                            <a href="/entity/detail/${id}">Detail</a>|
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

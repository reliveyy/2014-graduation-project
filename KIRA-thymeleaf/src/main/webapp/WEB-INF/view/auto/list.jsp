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
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld" %>
<html>
<head>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-theme.min.css">
</head>
<body class="container-fluid">
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Home</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="#">Batch Delete</a>
                </li>
                <li>
                    <a href="/auto/${entityName}/add" style="text-transform: capitalize">New ${entityName}</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
<div class="page-header">
    <h1 style="text-transform: capitalize">
        ${entityName} List
    </h1>
</div>

<c:if test=""
<div class="panel panel-default">
    <table class="table">
        <tr>
            <th></th>
            <c:forEach var="field" items="${fields}">
                <th>
                    <ex:displayName field="${field}"/>
                </th>
            </c:forEach>
            <th>Operations</th>
        </tr>
        <c:forEach var="item" items="${list}">
            <tr>
                <td><input type="checkbox"/></td>
                <c:forEach var="field" items="${fields}">

                    <td>
                        <ex:display field="${field}" value="${item[field.name]}"/>
                    </td>

                </c:forEach>
                <td>
                    <a href="/auto/${entityName}/detail/${item.id}">Detail</a> |
                    <a href="/auto/${entityName}/edit/${item.id}">Edit</a> |
                    <a href="/auto/${entityName}/delete/${item.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<ul class="pagination">
    <li>
        <a>«</a>
    </li>
    <li>
        <a>1</a>
    </li>
    <li class="disabled">
        <span>...</span>
    </li>
    <li>
        <a>4</a>
    </li>
    <li>
        <a>5</a>
    </li>
    <li>
        <a>6</a>
    </li>
    <li>
        <a>7</a>
    </li>
    <li>
        <a>8</a>
    </li>
    <li class="disabled">
        <span>...</span>
    </li>
    <li>
        <a>11</a>
    </li>
    <li>
        <a>»</a>
    </li>


</ul>
</body>
<script src="/static/js/jquery-2.1.0.min.js"></script>
<script src="/static/js/mustache.js"></script>
<script src="/static/js/less-1.7.0.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
</html>

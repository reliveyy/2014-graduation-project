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
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-theme.min.css">
</head>
<body class="container-fluid">
<div>
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
                <a class="navbar-brand" href="#">Brand</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/auto/${entityName}/list">List</a>
                    </li>
                    <li>
                        <a href="/auto/${entityName}/edit/${entity.id}">Edit</a>
                    </li>
                    <li>
                        <a href="/auto/${entityName}/delete/${entity.id}">Delete</a>
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
            ${entityName} Details
        </h1>
    </div>
    <div class="list-group">
    <c:forEach var="field" items="${fields}">
        <div class="list-group-item">
            <h4 class="list-group-item-heading">${field.name}</h4>
            <p class="list-group-item-text">
                ${entity[field.name]}
            </p>
        </div>
    </c:forEach>
    </div>
</div>
</body>
<script src="/static/js/jquery-2.1.0.min.js"></script>
<script src="/static/js/mustache.js"></script>
<script src="/static/js/less-1.7.0.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
</html>

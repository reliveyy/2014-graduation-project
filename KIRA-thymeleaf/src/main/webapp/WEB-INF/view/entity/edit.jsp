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
    <title></title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-theme.min.css">
    <style>
        .error{
            color: red;
        }
    </style>
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
                    <a href="/entity/list">Back to List</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
<div class="page-header">
    <h1 style="text-transform: capitalize">
        New Entity
    </h1>
</div>
<form:form commandName="kiraEntity">
    <div class="form-group">
        <label>Package Name</label>
        <div>
            <form:input path="packageName" cssClass="form-control"/>
            <form:errors path="packageName" cssClass="error"/>
        </div>
    </div>
    <div class="form-group">
        <label>Entity Name</label>
        <div>
            <form:input path="name" cssClass="form-control"/>
            <form:errors path="name" cssClass="error"/>
        </div>
    </div>
    <div class="form-group">
        <label>Code</label>
        <div>
            <form:textarea rows="15" path="code" cssClass="form-control"/>
            <form:errors path="code" cssClass="error" htmlEscape="false"/>
        </div>
    </div>
    <div class="form-group">
        <label>URL Name</label>
        <div>
            <form:input path="urlName" cssClass="form-control"/>
            <form:errors path="urlName" cssClass="error"/>
        </div>
    </div>
    <input type="submit" value="Submit" class="btn btn-default"/>
</form:form>
</body>
<script src="/static/js/jquery-2.1.0.min.js"></script>
<script src="/static/js/mustache.js"></script>
<script src="/static/js/less-1.7.0.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
</html>

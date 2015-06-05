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
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-theme.min.css">
    <style>
        .k-code {
            font-family: "Courier New", Courier, monospace;
        }

        .k-code-ln {
            color: #808080;
            font-size: small;
            padding-right: 10px;
        }

        tr:nth-child(even){
            background: #efefef;
        }
    </style>
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
                        <a href="/entity/list">List</a>
                    </li>
                    <li>
                        <a href="/entity/edit/${id}">Edit</a>
                    </li>
                    <li>
                        <a href="/entity/delete/${id}">Delete</a>
                    </li>
                </ul>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    <div class="page-header">
        <h1>
            Entity Details
        </h1>
    </div>
    <div class="list-group">
        <div class="list-group-item">
            <h4 class="list-group-item-heading">Package Name</h4>

            <p class="list-group-item-text">
                ${command.packageName}
            </p>
        </div>
        <div class="list-group-item">
            <h4 class="list-group-item-heading">Entity Name</h4>

            <p class="list-group-item-text">
                ${command.name}
            </p>
        </div>
        <div class="list-group-item">
            <h4 class="list-group-item-heading">URL Name</h4>

            <p class="list-group-item-text">
                ${command.urlName}
            </p>
        </div>
        <div class="list-group-item">
            <h4 class="list-group-item-heading">Code</h4>

            <p class="list-group-item-text">
                <ex:code content="${command.code}"/>
            </p>
        </div>
    </div>
</div>
</body>
<script src="/static/js/jquery-2.1.0.min.js"></script>
<script src="/static/js/mustache.js"></script>
<script src="/static/js/less-1.7.0.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
</html>

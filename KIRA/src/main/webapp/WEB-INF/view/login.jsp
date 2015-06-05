<%--
  Created by IntelliJ IDEA.
  User: com.oplogo.dashboard
  Date: 4/9/14
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head lang="en">
    <title>Login</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">


    <link rel="stylesheet" href="/res/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/res/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/res/css/signin.css">

    <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>

</head>
<body onload='document.loginForm.username.focus();'>
<div class="container">

    <form class="form-signin" role="form" action="<c:url value='/j_spring_security_check' />" method="post" autocomplete="off">
        <h1 class="form-signin-heading text-center" style="font-family: Lobster;">Dashboard</h1>
        <input type="text" class="form-control" placeholder="Email address / Employee ID" required
               autofocus name="username">
        <input type="password" class="form-control" placeholder="Password" required name="password">
        <label class="checkbox">
            <input type="checkbox" value="remember-me"> Remember me
        </label>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
        <div class="help-block text-center">
            Don't have an account? <a href="/register">Sign up now</a>
        </div>
    </form>

    <button type="button" class="btn btn-default btn-lg">
        <span class="glyphicon glyphicon-question-sign"></span> Star
    </button>
</div>
</body>
<script src="/res/js/jquery-2.1.0.min.js"></script>
<script src="/res/js/less-1.5.0.min.js"></script>
<script src="/res/bootstrap/js/bootstrap.min.js"></script>
</html>

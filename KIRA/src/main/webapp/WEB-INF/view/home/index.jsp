<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 5/19/14
  Time: 9:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script>
        $(document).ready(function(){
            $("#init-auto-controller").click(function(){
                $("#msgbox").load("/home/init/auto-controller");
            });
        });
    </script>
</head>
<body>
<div class="ui menu" style="border-radius: 0;margin-top: 0px;">
    <a class="item">
        <i class="mail icon"></i> Messages
    </a>
</div>
<div id="container">
    <div id="msgbox" class="ui info message"></div>
    <button id="init-auto-controller" class="ui teal button">Init Auto Controller</button>
</div>
</body>
</html>

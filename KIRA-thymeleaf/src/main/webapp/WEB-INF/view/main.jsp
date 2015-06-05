<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> ${applicationScope.logo} </title>
    <link rel="stylesheet" href="/static/semantic-ui/css/semantic.min.css"/>
    <link rel="stylesheet/less" href="/static/css/home.less"/>
    <script src="/static/js/jquery-2.1.0.min.js"></script>
    <script src="/static/js/less-1.7.0.min.js"></script>
    <script src="/static/semantic-ui/javascript/semantic.min.js"></script>
    <script>
        $(document).ready(function () {
            var nav = $("#nav");
            var main = $("#main");
            if ($(window).width() - nav.width() > main.width()) {
                main.width($(window).width() - nav.width());
            }
            $(window).resize(function () {
                main.width($(window).width() - nav.width());
            });
            main.load("/home");

            var openInMain = function () {
                var href = $(this).attr('href');
                main.load(href, function () {
                    //alert("loaded");
                    $("#main a").click(openInMain)
                });
                return false;
            };
            $("#nav a").click(openInMain);
        });
    </script>
</head>
<body>
<aside id="nav">
    <div id="avatar-box">
        <div id="avatar-wrapper">
            <img id="avatar" src="/static/img/avatar.jpg"/>

            <div id="avatar-cover"></div>
        </div>

        <div hidden id="avatar_menu">
            <a href="#logout">Log out</a>
            <a href="#change-avatar">Change avatar</a>
        </div>
    </div>
    <ul id="menu">
        <li>
            <a href="/home" target="main">home</a>
        </li>
        <li>
            <a href="/entity/list" target="main">Entity</a>
        </li>
        <li>
            <a href="/data-source" target="main">data source</a>
        </li>
        <li>
            <a href="/data-source" target="main">help</a>
        </li>
        <li>
            <a href="/data-source" target="main">logout</a>
        </li>
    </ul>
</aside>
<section id="main">

</section>

</body>
</html>

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
    <script></script>
</head>
<body>
<div class="ui menu" style="border-radius: 0;margin-top: 0px;">
    <div class="title item">
        Message
    </div>
    <a class="item">
        <i class="home icon"></i> Home
    </a>
    <a class="item">
        <i class="mail icon"></i> Messages
    </a>
    <a class="item">
        <i class="user icon"></i> Friends
    </a>
    <div class="item">
        <div class="ui action input">
            <input type="text" placeholder="Navigate to...">
            <div class="ui button">Go</div>
        </div>
    </div>
    <div class="right item">
        <div class="ui icon input">
            <input type="text" placeholder="Search...">
            <i class="search icon"></i>
        </div>
    </div>
</div>
<div style="padding:20px;">
<h1>Welcome</h1>

    This is main
<div class="ui two column grid">
    <div class="column">
        <div class="ui piled blue segment">
            <h2 class="ui header">
                <i class="icon inverted circular blue comment"></i> Messages
            </h2>
            <div class="ui comments">
                <div class="comment">
                    <a class="avatar">
                        <img src="/static/img/animals/frog.png">
                    </a>
                    <div class="content">
                        <a class="author">Dog Doggington</a>
                        <div class="metadata">
                            <span class="date">2 days ago</span>
                        </div>
                        <div class="text">
                            I think this is a great idea and i am voting on it
                        </div>
                        <div class="actions">
                            <a class="reply">Reply</a>
                            <a class="delete">Delete</a>
                        </div>
                    </div>
                </div>
                <div class="comment">
                    <a class="avatar">
                        <img src="/static/img/animals/shark.png">
                    </a>
                    <div class="content">
                        <a class="author">Pawfin Dog</a>
                        <div class="metadata">
                            <span class="date">2 days ago</span>
                        </div>
                        <div class="text">
                            I think this is a great idea and i am voting on it
                        </div>
                        <div class="actions">
                            <a class="reply">Reply</a>
                            <a class="delete">Delete</a>
                        </div>
                    </div>
                </div>
                <div class="comment">
                    <a class="avatar">
                        <img src="/static/img/animals/bird.png">
                    </a>
                    <div class="content">
                        <a class="author">Dog Doggington</a>
                        <div class="metadata">
                            <span class="date">2 days ago</span>
                        </div>
                        <div class="text">
                            I think this is a great idea and i am voting on it
                        </div>
                        <div class="actions">
                            <a class="reply">Reply</a>
                            <a class="delete">Delete</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>

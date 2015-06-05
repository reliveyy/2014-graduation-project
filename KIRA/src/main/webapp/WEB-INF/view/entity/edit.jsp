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
        function isEmpty( el ){
            return !$.trim(el.html())
        }
        $(document).ready(function(){
            $('.ui.red.pointing.above.ui.label').each(function(){
                if(isEmpty($(this))){
                    $(this).remove();
                }else{
                    $(this).parent().addClass("error");
                    $(this).parent().click(function(){
                        $(this).removeClass("error");
                    });
                }
            });
        });
    </script>
</head>
<body>
<div class="ui menu" style="border-radius: 0;margin-top: 0px;">
    <a class="item" href="/entity/list">
        <i class="left icon"></i> Back to list
    </a>
</div>
<div id="container">
    <h2 class="ui header">
        Edit Entity
    </h2>
    <form:form cssClass="ui form segment" commandName="aut" autocomplete="off">
        <c:forEach var="field" items="${fields}">
            <div class="field">
                <label>${field.displayName}</label>
                <c:choose>
                    <c:when test="${field.type == 'TEXT_AREA'}">
                        <div>
                            <form:textarea path="${field.name}" cssStyle="${field.cssStyle}" rows="${field.rows}"
                                           placeholder="${field.hint}"/>

                        </div>
                    </c:when>
                    <c:otherwise>
                        <div>
                            <form:input path="${field.name}" cssStyle="${field.cssStyle}" placeholder="${field.hint}"/>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="ui red pointing above ui label" hidden="hidden">
                    <form:errors path="${field.name}" htmlEscape="false" cssStyle="text-transform: none"/>
                </div>
            </div>
        </c:forEach>
        <input type="submit" value="Save Changes" class="ui blue submit button"/>
    </form:form>
</div>
</body>
</html>

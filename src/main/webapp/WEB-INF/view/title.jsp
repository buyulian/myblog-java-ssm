<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>博客列表</title>
</head>
<body>
<div>
    <c:if test="${!empty titleList}">
        <c:forEach var="Text" items="${titleList}">
            <a href="seeBlog?id=${Text.id}">标题：${Text.title}</a> &nbsp;&nbsp;<br>
        </c:forEach>
    </c:if>
</div>
<div>
    <a href="signIn">
        <input type="button" value="登录" class="btn btn-default">
    </a>
    <a href="addBlog">
        <input type="button" value="添加" class="btn btn-default">
    <a href="loginOut">
        <input type="button" value="退出" class="btn btn-default">
    </a>
    </a>
</div>
</body>
</html>

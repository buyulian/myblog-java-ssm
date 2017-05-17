<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>用户信息列表</title>
</head>
<body>
<div>
    <c:if test="${!empty userList}">
        <c:forEach var="user" items="${userList}">
            用户名：${user.id} &nbsp;&nbsp;
            角色：${user.role} &nbsp;&nbsp;
            <a href="deleteUser?id=${user.id}">删除</a><br>
        </c:forEach>
    </c:if>
</div>
<div>
    <a href="signUp">
        <input type="button" value="添加" class="btn btn-default">
    </a>
    <a href="loginOut">
        <input type="button" value="退出" class="btn btn-default">
    </a>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: 不语恋
  Date: 2017/5/12
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>查看博客</title>

</head>

<body>
<div>
    <label id="title">${text.title}</label>
</div>
<!--用父容器来控制宽度-->
<div style="width:90%">
    <!--用当前元素来控制高度-->
    <div id="div1" style="height:400px;max-height:500px;">
        <p>${text.content}</p>
    </div>
</div>
<div>
    <a href="/">查看标题</a>
    <a href="text?id=${text.id}">编辑内容</a>
</div>
</body>
</html>

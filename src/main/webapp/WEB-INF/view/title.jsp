<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.text.DateFormat"  %>
<%@ page import="static java.awt.SystemColor.text" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>

    <link rel="shortcut icon" href="/img/blog.ico" type="image/x-icon">
    <link rel="icon" href="/img/blog.ico" type="image/x-icon">

    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <link href="/css/syntax.css" rel="stylesheet" type="text/css">
    <link href="/css/responsive.css" rel="stylesheet" type="text/css">
    <link href="/css/common.css" rel="stylesheet" type="text/css">


    <!--引入jquery和wangEditor.js-->   <!--注意：javascript必须放在body最后，否则可能会出现问题-->
    <script type="text/javascript" src="/wangEditor/dist/js/lib/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/js/jquery.base64.js"></script>
    <script>
        function replaceAll(s1,s2,str){
            var n=str.length;
            var str2="";
            for(var i=0;i<n;i++){
                if(str[i]==s1)
                    str2+=s2;
                else
                    str2+=str[i];
            }
            return str2;
        }
        function decode(str) {
            var mod4 = str.length%4;
            if(mod4 > 0){
                str = str + "====".substring(mod4);
            }
            str=replaceAll('_','/',str);
            str=replaceAll('-','+',str);
            str=$.base64.decode(str);
            str=decodeURI(str);
            return str;
        }
    </script>
</head>
<body>
<div id="u">
    <c:if test="${role=='admin'}">
        <a href="showUser">
            <small class="datetime muted">管理</small>
        </a>
    </c:if>
    <c:if test="${role!=null}">
        <a href="addBlog">
            <small class="datetime muted">添加</small>
        </a>
        <a href="loginOut">
            <small class="datetime muted">退出</small>
        </a>
        <a href="deleteUnusedImage">
            <small class="datetime muted">清理无用图片</small>
        </a>
        <a href="signIn">
            <small class="datetime muted">登录</small>
        </a>
    </c:if>
</div>
<div class="center">
    <img src="/img/logo.jpg" alt="logo" width="66" height="66"></a>
    <p>buyulian</p>
    <p>自强不息</p>
</div>
<div id="container">
    <div class="content">
        <ul class="posts">
            <c:if test="${!empty titleList}">
                <c:forEach var="text" items="${titleList}">
                    <li>
                        <a href="seeBlog?id=${text.id}">
                            <script>document.write(decode("${text.title}"))</script>
                        </a>
                        <c:if test="${isAdmin!=null}">
                            <a href="deleteBlog?id=${text.id}" class="remove">删除</a>
                        </c:if>
                        <small class="datetime muted">${text.date.year+1900}-${text.date.month+1}-${text.date.date}</small>
                        &nbsp;&nbsp;<br>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>

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
<div id="titleDiv">
    <c:if test="${!empty titleList}">
        <c:forEach var="text" items="${titleList}">
            <a href="seeBlog?id=${text.id}">标题：<script>document.write(decode("${text.title}"))</script></a>
            <c:if test="${isAdmin!=null}">
                <a href="deleteBlog?id=${text.id}">删除</a>
            </c:if>
            &nbsp;&nbsp;<br>
        </c:forEach>
    </c:if>
</div>
<div>
    <a href="signIn">
        <input type="button" value="登录" class="btn btn-default">
    </a>
    <c:if test="${isAdmin!=null}">
        <a href="addBlog">
            <input type="button" value="添加" class="btn btn-default">
        <a href="loginOut">
            <input type="button" value="退出" class="btn btn-default">
        </a>
        <a href="deleteUnusedImage">
            <input type="button" value="清理无用图片" class="btn btn-default">
        </a>
    </c:if>

    </a>
</div>
</body>
</html>

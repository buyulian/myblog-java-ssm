<%--
  Created by IntelliJ IDEA.
  User: 不语恋
  Date: 2017/5/12
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title></title>
    <!--引入jquery和wangEditor.js-->   <!--注意：javascript必须放在body最后，否则可能会出现问题-->
    <script type="text/javascript" src="/wangEditor/dist/js/lib/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/js/jquery.base64.js"></script>
    <script>
        window.onload = function() {

            var str="${text.content}";
            var mod4 = str.length%4;
            if(mod4 > 0){
                str = str + "====".substring(mod4);
            }
            str=replaceAll('_','/',str);
            str=replaceAll('-','+',str);
            str=$.base64.decode(str);
            str=decodeURI(str);

            document.getElementById("div1").innerHTML="<p>"+str+"</p>";
            document.getElementsByTagName("title")[0].innerHTML="查看："+decode("${text.title}");
        }
    </script>
    <script>
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
        function replaceAll(s1,s2,str){
            var n=str.length;
            for(var i=0;i<n;i++){
                if(str[i]==s1)
                    str=str.replace(s1,s2);
            }
            return str;
        }
    </script>
</head>

<body>
<div>
    <h1><script>document.write(decode("${text.title}"))</script></h1>
</div>
<div>
    <a href="/">首页</a>
    <c:if test="${isAdmin!=null}">
        <a href="text?id=${text.id}">编辑</a>
    </c:if>
    <c:if test="${priText!=null}">
        <a href="seeBlog?id=${priText.id}">上一篇：<script>document.write(decode("${priText.title}"))</script></a>
    </c:if>
    <c:if test="${priText==null}">
        <label>上一篇：没有了</label>
    </c:if>
    <c:if test="${nextText!=null}">
        <a href="seeBlog?id=${nextText.id}">下一篇：<script>document.write(decode("${nextText.title}"))</script></a>
    </c:if>
    <c:if test="${nextText==null}">
        <label>下一篇：没有了</label>
    </c:if>
</div>
<!--用父容器来控制宽度-->
<div>
    <!--用当前元素来控制高度-->
    <div id="div1">
    </div>
</div>
</body>
</html>

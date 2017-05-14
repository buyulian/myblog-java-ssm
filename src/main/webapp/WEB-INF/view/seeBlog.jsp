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
            str=unescape(str);

            document.getElementById("div1").innerHTML="<p>"+str+"</p>";
        }
    </script>
    <script>
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
    <label id="title">${text.title}</label>
</div>
<!--用父容器来控制宽度-->
<div style="width:90%">
    <!--用当前元素来控制高度-->
    <div id="div1" style="height:400px;max-height:500px;">
    </div>
</div>
<div>
    <a href="/">查看标题</a>
    <a href="text?id=${text.id}">编辑内容</a>
</div>
</body>
</html>

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
    <title>富文本编辑器</title>
    <!--引入wangEditor.css-->
    <link rel="stylesheet" type="text/css" href="/wangEditor/dist/css/wangEditor.min.css">
    <!--引入jquery和wangEditor.js-->   <!--注意：javascript必须放在body最后，否则可能会出现问题-->
    <script type="text/javascript" src="/wangEditor/dist/js/lib/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/wangEditor/dist/js/wangEditor.min.js"></script>
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
        }
    </script>
</head>

<body>
<div>
    <input type="text" value="${text.title}" id="title">
    <button onclick="save()">保存</button>
    <a href="/">查看</a>
</div>
<!--用父容器来控制宽度-->
<div>
    <!--用当前元素来控制高度-->
    <div id="div1" style="height:600px;max-height:1600px;">
    </div>
</div>
<script>

    var editor = new wangEditor('div1');

    // 上传图片（举例）
    editor.config.uploadImgUrl = '/upload';

    // 配置自定义参数（举例）
    //    editor.config.uploadParams = {
    //        token: 'abcdefg',
    //        user: 'wangfupeng1988'
    //    };

    // 设置 headers（举例）
    editor.config.uploadHeaders = {
        'Accept' : 'text/x-json'
    };

    // 隐藏掉插入网络图片功能。该配置，只有在你正确配置了图片上传功能之后才可用。
    //editor.config.hideLinkImg = true;

    editor.create();
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
    function save() {
        // 获取编辑器区域完整html代码
        var html = editor.$txt.html();
        var xmlhttp;
        if (window.XMLHttpRequest)
        {
            // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
            xmlhttp=new XMLHttpRequest();
        }
        else
        {
            // IE6, IE5 浏览器执行代码
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200){
                alert("保存成功");
            }
        }
        html=encodeURI(html);
        html=$.base64.encode(html);
//        html=$.base64.decode(html);
//        html=unescape(html);
        html=replaceAll('+','-',html);
        html=replaceAll('/','_',html);
        html=replaceAll('=','',html);
        var title=document.getElementById("title").value;
        xmlhttp.open("POST","/save",true);
        xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlhttp.send("content="+html+"&id=${text.id}&title="+title);
    }
</script>
</body>

</html>

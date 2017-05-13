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

</head>

<body>
<div>
    <input type="text" value="${text.title}" id="title">
</div>
<!--用父容器来控制宽度-->
<div style="width:90%">
    <!--用当前元素来控制高度-->
    <div id="div1" style="height:400px;max-height:500px;">
        <p>${text.content}</p>
    </div>
</div>
<!--这里引用jquery和wangEditor.js-->
<script type="text/javascript">
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
    editor.config.hideLinkImg = true;

    editor.create();
</script>
<div>
    <button onclick="save()">保存</button>
    <a href="/">查看</a>
</div>
<script>
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
        var title=document.getElementById("title").value;
        xmlhttp.open("POST","/save",true);
        xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlhttp.send("content="+html+"&id=${text.id}&title="+title);
    }
</script>
</body>

</html>

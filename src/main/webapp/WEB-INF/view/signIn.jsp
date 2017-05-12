<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>登录</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">

</head>

<body>
<div class="page-header">
    <h1 id="title">我的博客</h1>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h1>请登录</h1>
                    <h3>${message}</h3>
                </div>
                <div class="panel-body">
                    <form role="form" action="${pageContext.request.contextPath}/login" method="post">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="学号" name="id" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="密码" name="password" type="password">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input name="remember" type="checkbox" value="RememberMe">记住我
                                </label>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" value="登录" class="btn btn-default">
                                <a href="signUp">
                                    <input type="button" value="注册" class="btn btn-default">
                                </a>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>

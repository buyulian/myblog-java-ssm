<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>用户信息列表</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div>
                <h1 class="page-header">添加用户</h1>
            </div>
            <div class="panel-heading">
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            用户信息
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <form action="${pageContext.request.contextPath}/addUser" method="post">
                                    <label>用户名</label>
                                    <input class="form-control" name="id">
                                    <label>密码</label>
                                    <input type="password" class="form-control" name="password">
                                    <label>角色列表(按住shift键多选)</label>
                                    <select multiple="true" class="form-control" name="role">
                                        <option value="admin">管理员</option>
                                        <option value="standard">标准</option>
                                    </select>

                                    <button type="submit"
                                            class="btn btn-primary form-control">添加
                                    </button>
                                </form>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
        </div>

        <!-- /.container-fluid -->
    </div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>modify user information</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/add.css">
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">
                        user information
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="jumbotron">
                <h1>Hello, ${user.username}!</h1>
                <p>please modify after consideration ^_^</p>
            </div>
            <div class="page-header">
                <h3><small>个人信息</small></h3>
            </div>
            <form class="form-horizontal" action="<%=request.getContextPath()%>/editUser.do" method="post">
                <input id="id" name="id" type="hidden" value="${user.id}">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">User ：</label>
                    <div class="col-sm-6">
                        <input name="name" class="form-control" id="name" value="${user.username}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">Password ：</label>
                    <div class="col-sm-6">
                        <input name="password" class="form-control" id="password" value="${user.password}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="realName" class="col-sm-2 control-label">Name ：</label>
                    <div class="col-sm-8">
                        <input name="realName" class="form-control" id="realName" value="${user.real_name}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="birthday" class="col-sm-2 control-label">Birthday ：</label>
                    <div class="col-sm-8">
                        <input name="birthday" class="form-control" id="birthday" value="${user.birthday}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label">Phone ：</label>
                    <div class="col-sm-8">
                        <input name="phone"  class="form-control" id="phone" value="${user.phone}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="address" class="col-sm-2 control-label">Address ：</label>
                    <div class="col-sm-8">
                        <input name="address"  class="form-control" id="address" value="${user.address}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Save</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>

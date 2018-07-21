<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My Information</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/add.css">
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/message/list.do">
                        return to message board
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="jumbotron">
                <h1>Hello, ${user.username}!</h1>
                <p>All information are listed here ^_^</p>
            </div>
            <div class="page-header">
                <h3><small>个人信息</small></h3>
            </div>
            <form class="form-horizontal" action="<%=request.getContextPath()%>/editUserPrompt.do" method="post">
                <input type="hidden" id="id" name="id" value="${user.id}">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">User ：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="name" value="${user.username}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="realName" class="col-sm-2 control-label">Name ：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="realName" value="${user.real_name}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="birthday" class="col-sm-2 control-label">Birthday ：</label>
                    <div class="col-sm-8">
                        <input name=""  class="form-control" rows="3" id="birthday" value="${user.birthday}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label">Phone ：</label>
                    <div class="col-sm-8">
                        <input name=""  class="form-control" rows="3" id="phone" value="${user.phone}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="address" class="col-sm-2 control-label">Phone ：</label>
                    <div class="col-sm-8">
                        <input name=""  class="form-control" rows="3" id="address" value="${user.address}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Modify</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>

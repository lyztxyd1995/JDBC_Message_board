<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>edit messages</title>
        <link rel="stylesheet" href="../../../css/bootstrap.min.css">
        <link rel="stylesheet" href="../../../css/add.css">
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/message/list.do">
                        edit messages
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="page-header">
                <h3><small>edit messages</small></h3>
            </div>
            <form class="form-horizontal" action="<%=request.getContextPath()%>/editMessage.do" method="post">
                <div class="form-group">
                    <label for="inputTitle" class="col-sm-2 control-label">Title ：</label>
                    <div class="col-sm-8">
                        <input name="title" class="form-control" id="inputTitle" value="${message.title}">
                    </div>
                </div>
                <input type = "hidden" name="message_id" value="${message.id}">
                <div class="form-group">
                    <label for="inputContent" class="col-sm-2 control-label">Content ：</label>
                    <div class="col-sm-8">
                        <textarea name="content"  class="form-control" rows="3" id="inputContent">${message.content}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">publish messages</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
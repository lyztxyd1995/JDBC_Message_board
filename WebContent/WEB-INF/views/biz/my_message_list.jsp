<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My Message Board</title>
        <link rel="stylesheet" href="../css/index.css">
        <script type="text/javascript">
        		function submitEdit(e){
        			var msg_id = e.getAttribute("message_id");
        			var url = "<%=request.getContextPath()%>/EditMessagePrompt.do?editMessage_id=" + msg_id;
        			window.location.href= url;
        		}
        		function submitDelete(e){
        			var msg_id = e.getAttribute("message_id");
        			var url = "<%=request.getContextPath()%>/deleteMessage.do?delete_id=" + msg_id;
        			window.location.href= url;
        		}
            function submitMessageForm(flag) {
                if ('first' == flag) {
                    document.getElementById('page').value = 1;
                } else if ('pre' == flag) {
                    var current = Number(document.getElementById('page').value);
                    if (current > 1) {
                        document.getElementById('page').value = current - 1;
                    }
                } else if ('next' == flag) {
                    var current = Number(document.getElementById('page').value);
                    var last = Number(document.getElementById('last').value);
                    if (current < last) {
                        document.getElementById('page').value = current + 1;
                    }
                } else if ('last' == flag) {
                    var last = Number(document.getElementById('last').value);
                    document.getElementById('page').value = last < 1 ? 1 : last;
                }
                document.getElementById('messageForm').submit();
            }
            
            
        </script>
    </head>

    <body>
        <header>
            <div class="container">
                    <nav>
                        <a href="<%=request.getContextPath()%>/message/list.do">All messages</a>
                    </nav>
                    <nav>
                        <a href="<%=request.getContextPath()%>/userInfo.do">My Info</a>
                    </nav>
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>Message Board</h1>
                    <p>Message Board, Add messages Here</p>
                </div>
            </div>
        </section>
        <section class="main">
            <div class="container">
                <c:forEach items="${messages}" var="msg">
                		<br>
                		<br>
                    <div class="alt-item">
                        <div class="alt-head">
                            <div class="alt-info">
                                <span>Author：<a href="">${msg.username}</a></span>
                                <span>Date：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${msg.createtime}"/></span>                                
                            </div>
                        </div>
                        <div class="alt-content">
                            <h3>${msg.title}</h3>
                            <p>${msg.content}</p>
                        </div>
                        <button message_id =${msg.id} type="button" onclick="submitEdit(this)">Edit</button>
                        &nbsp; &nbsp;
                        <span><button message_id =${msg.id} type="button" onclick="submitDelete(this)">Delete</button></span>
                    </div>
                    <br>
                		<br>
                </c:forEach>
            </div>
        </section>
        <section class="page">
            <div class="container">
                <div id="pagefy">
                    <ul>
                        <form id="messageForm" action="<%=request.getContextPath()%>/my/message/list.do" method="post">
                            <input type="hidden" id="page" name="page" value="${page}">
                            <input type="hidden" id="last" name="last" value="${last}">
                            <li><a href="javascript:void(0)" onclick="submitMessageForm('first')">First page</a></li>
                            <li><a href="javascript:void(0)" onclick="submitMessageForm('pre')">prev page</a></li>
                            <li><a href="javascript:void(0)">The${page}page</a></li>
                            <li><a href="javascript:void(0)" onclick="submitMessageForm('next')">Next page</a></li>
                            <li><a href="javascript:void(0)" onclick="submitMessageForm('last')">Last page</a></li>
                        </form>
                    </ul>
                </div>
            </div>
        </section>
    </body>
</html>
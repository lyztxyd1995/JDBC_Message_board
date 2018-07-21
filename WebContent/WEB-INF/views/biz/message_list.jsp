<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Message board</title>
        <link rel="stylesheet" href="../css/index.css">
        <script type="text/javascript">
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
                <% if (null != request.getSession().getAttribute("user")) {%>
                    <nav>
                        <a href="<%=request.getContextPath()%>/my/message/list.do">My messages</a>
                    </nav>
                    <nav>
                        <a href="<%=request.getContextPath()%>/userInfo.do">My information</a>
                    </nav>
                <%} else { %>
                    <nav>
                        <a href="<%=request.getContextPath()%>/login.do">Login</a>
                        <a href="<%=request.getContextPath()%>/regPrompt.do">Register</a>
                    </nav>
                <% } %>
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>Message Board</h1>
                    <p>Adding Messages here </p>
                </div>
            </div>
        </section>
        <section class="main">
            <div class="container">
                <c:forEach items="${messages}" var="msg">
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
                    </div>
                </c:forEach>
            </div>
        </section>
        <section class="page">
            <div class="container">
                <% if (null != request.getSession().getAttribute("user")) {%>
                    <div id="fatie">
                        <a href="<%=request.getContextPath()%>/addMessagePrompt.do"><button>Click me to leave message</button></a>
                    </div>
                <%} else { %>
                    <div id="fatie">
                        Please<a href="<%=request.getContextPath()%>/login.do"><button>Login</button></a> and then leave messages
                    </div>
                <% } %>


                <div id="pagefy">
                    <ul>
                        <form id="messageForm" action="<%=request.getContextPath()%>/message/list.do" method="post">
                            <input type="hidden" id="page" name="page" value="${page}">
                            <input type="hidden" id="last" name="last" value="${last}">
                            <li><a href="javascript:void(0)" onclick="submitMessageForm('first')">First page</a></li>
                            <li><a href="javascript:void(0)" onclick="submitMessageForm('pre')">prev page</a></li>
                            <li><a href="javascript:void(0)">The ${page} page</a></li>
                            <li><a href="javascript:void(0)" onclick="submitMessageForm('next')">Next page</a></li>
                            <li><a href="javascript:void(0)" onclick="submitMessageForm('last')">Last page</a></li>
                        </form>
                    </ul>
                </div>
            </div>
        </section>
    </body>
</html>
package jdbc.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.bean.Message;
import jdbc.bean.User;
import jdbc.service.MessageService;

public class MessageServlet extends HttpServlet {
	private MessageService messageService;
	public void init() {
		messageService = new MessageService();
	}
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathName = request.getServletPath();
		if(pathName.equals("/addMessagePrompt.do")) {
			request.getRequestDispatcher("/WEB-INF/views/biz/add_message.jsp").forward(request, response);
		} else if (pathName.equals("/addMessage.do")) {
			User user = (User)request.getSession().getAttribute("user");
			if (user == null) {
				request.getRequestDispatcher("/message/list.do").forward(request, response);
			} else {
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				boolean result = messageService.addMessage(new Message(user.getId(), user.getUsername(), title, content));
				if(result) {
					request.getRequestDispatcher("/message/list.do").forward(request, response);
				} else {
					request.getRequestDispatcher("/WEB-INF/views/biz/add_message.jsp").forward(request, response);
				}
			}
		} else {
			request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
		}
	}
}

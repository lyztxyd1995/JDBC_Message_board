package jdbc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.bean.User;
import jdbc.service.MessageService;

public class EditMessageServlet extends HttpServlet{
	private MessageService messageService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		messageService = new MessageService();
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Long id = Long.valueOf(request.getParameter("message_id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		if(id == null || title == null || content == null) {
			request.getRequestDispatcher("/my/message/list.do").forward(request, response);
		}
		boolean result = messageService.updateMessage(id, title, content);
		if(result) {
			request.getRequestDispatcher("/my/message/list.do").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		messageService = null;
	}
}

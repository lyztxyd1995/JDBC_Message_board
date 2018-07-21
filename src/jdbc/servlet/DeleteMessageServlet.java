package jdbc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.bean.Message;
import jdbc.service.MessageService;

public class DeleteMessageServlet extends HttpServlet{
	private MessageService messageService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		messageService = new MessageService();
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Long id = Long.valueOf(request.getParameter("delete_id"));
		if(id == null) {
			System.out.println("id is null");
			request.getRequestDispatcher("/WEB-INF/views/biz/my_message_list.jsp").forward(request, response);
		}
		boolean result = messageService.deleteMessage(id);
		if(result) {
			System.out.println("delete: " + id + " success");
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

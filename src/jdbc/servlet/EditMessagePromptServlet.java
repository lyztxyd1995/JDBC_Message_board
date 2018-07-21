package jdbc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.bean.Message;
import jdbc.service.MessageService;

public class EditMessagePromptServlet extends HttpServlet{
	private MessageService messageService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		messageService = new MessageService();
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Long id = Long.valueOf(request.getParameter("editMessage_id"));
		if(id == null) {
			request.getRequestDispatcher("/WEB-INF/views/biz/my_message_list.jsp").forward(request, response);
		}
		Message message = messageService.queryMessage(id);
		if(message == null) {
			request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/views/biz/edit_message.jsp").forward(request, response);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		messageService = null;
	}	
}

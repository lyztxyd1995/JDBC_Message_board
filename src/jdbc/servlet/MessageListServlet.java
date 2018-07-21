package jdbc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.bean.Message;
import jdbc.service.MessageService;

public class MessageListServlet extends HttpServlet{
	private MessageService messageService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		messageService = new MessageService();
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String pageStr = request.getParameter("page");
		int page = 1;
		if(pageStr != null && !pageStr.equals("")) {
			page = Integer.parseInt(pageStr);
		}
		List<Message> messages = messageService.getMessages(page, 5);
        int count = messageService.countMessages();
        int last = count % 5 == 0 ? (count / 5) : ((count / 5) + 1);
        request.setAttribute("last", last);
        request.setAttribute("messages", messages);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/WEB-INF/views/biz/message_list.jsp").forward(request, response);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		messageService = null;
	}
	
}

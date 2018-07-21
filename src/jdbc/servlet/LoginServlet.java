package jdbc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.bean.Message;
import jdbc.bean.User;
import jdbc.service.MessageService;
import jdbc.service.UserService;

public class LoginServlet extends HttpServlet{
	private UserService userService;
	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user  = userService.login(username, password);
		if(user != null) {
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/message/list.do").forward(request, response);
		} else {
			request.getRequestDispatcher("/login.do").forward(request, response);
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
	

}

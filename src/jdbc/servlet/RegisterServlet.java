package jdbc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.bean.User;
import jdbc.service.UserService;

public class RegisterServlet extends HttpServlet{
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
		String password1 = request.getParameter("password1");
		boolean result = true;
		String errorMessage = "";
		if(password.length() <= 6) {
			errorMessage = "密码不能小于6位";
			result = false;
		}
		if(password.length() > 16) {
			errorMessage = "密码不能大于16位";
			result = false;
		}
		if(!password.equals(password1)) {
			errorMessage = "密码不一致";
			result = false;
		}
		if(username == null || password==null || password1 == null) {
			errorMessage = "用户名或密码不能为空";
			result = false;			
		}
		if(result) {
			User user = new User(username, password);
			result = userService.registerUser(user);
			if(result == false) {
				errorMessage = "该用户名已经被注册，请更换用户名";
			}
		}
		if(result) {
			request.getRequestDispatcher("/login.do").forward(request, response);
		} else {
			request.getSession().setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/WEB-INF/views/biz/register.jsp").forward(request, response);
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
}

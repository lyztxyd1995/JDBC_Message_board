package jdbc.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.bean.User;
import jdbc.service.UserService;

public class UserServlet extends HttpServlet{
	private UserService userService;
	public UserServlet() {
		this.userService = new UserService();
	}
	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = request.getServletPath();
		if(path.equals("/userInfo.do")) {
			request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request, response);
		}
		else if(path.equals("/editUserPrompt.do")) {
			long id = Long.valueOf(request.getParameter("id"));
			User user = userService.getUserById(id);
			if(user!=null) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/views/biz/edit_user.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request, response);
			}
		}
		else if(path.equals("/editUser.do")) {
			Long id = Long.valueOf(request.getParameter("id"));
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String realName = request.getParameter("realName");
			String birthday = request.getParameter("birthday");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			User user = new User();
			user.setId(id);
			user.setPassword(password);
			user.setReal_name(realName);
			user.setPhone(phone);
			try {
				user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("format date error");
				e.printStackTrace();
			}
			user.setAddress(address);
			user.setUsername(name);
			boolean result = userService.updateUser(user);
			if(result) {
				request.getSession().setAttribute("user", user);
				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
		}
	}
}

package jdbc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterPrompyServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/views/biz/register.jsp").forward(request, response);
	}
}

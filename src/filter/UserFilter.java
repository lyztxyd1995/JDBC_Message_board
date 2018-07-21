package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import jdbc.bean.User;

public class UserFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		User user = (User)((HttpServletRequest)servletRequest).getSession().getAttribute("user");
		servletRequest.setAttribute("user", user);
		filterChain.doFilter(servletRequest, servletResponse);
	}
	
}

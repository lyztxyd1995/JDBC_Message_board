package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetEncodingFilter implements Filter {
	private String encoding;
	@Override
	public void init(FilterConfig filterConfig) {
		this.encoding = filterConfig.getInitParameter("encoding");
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		arg0.setCharacterEncoding(encoding);
		arg2.doFilter(arg0, arg1);
	}

}

package demo.thymeleaf;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class CSRFFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		//	フィルター追加
		/*
		if (request.getMethod().equals("POST")) {
		    String requestToken = request.getParameter(eps.getCsrfTokenParameter());
		    try {
		        exploitProtectionService.compareToken(requestToken);
		    } catch (ServiceException e) {
		        throw new ServletException(e);
		    }
		}
		
		chain.doFilter(request, response);
		*/
		
	}
}
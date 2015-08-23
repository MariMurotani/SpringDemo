package demo.thymeleaf;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.GenericFilterBean;

import demo.configs.Const;
import demo.configs.ConstError;

public class CSRFFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest baseRequest, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) baseRequest;

		/*boolean error = false;
		if (request.getMethod().equals("POST")) {
			String CSRFKey = "";
			request.getParameterNames();
			Enumeration e1 = request.getAttributeNames();
		    while (e1.hasMoreElements()) {
		    	String name = (String) e1.nextElement();
		    	if(name.startsWith(Const.CRSF_PREFIX)){
		    		CSRFKey = name;
		    		error = true;
		    		break;
		    	}
		    }
		    
			HttpSession httpSession = request.getSession();
			Enumeration e2 = httpSession.getAttributeNames();
		    while (e2.hasMoreElements()) {
		    	String name = (String) e2.nextElement();
		    	if(name.equals(CSRFKey)){
		    		String sessionK = (String) httpSession.getAttribute(CSRFKey);
		    		String requestK = (String) request.getAttribute(CSRFKey);
		    		if(sessionK != null && requestK != null){
		    			if(0 < requestK.length() && sessionK.equals(requestK)){
		    				error = false;
		    			}
		    		}
		    	}
		    }
		}
		if(error==false){
			new Exception(ConstError.CRSF_ERR);
		}
		*/
		chain.doFilter(request, response);
		
	}
}
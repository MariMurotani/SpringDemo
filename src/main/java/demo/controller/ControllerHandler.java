package demo.controller;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import demo.annotation.ScreenTrans;
import demo.configs.Const;
import demo.configs.ConstError;


public class ControllerHandler implements HandlerInterceptor  {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		//System.out.println("---Before Method Execution---");
		
		// リファラー判定
		if(handler instanceof HandlerMethod){
			//	メソッドのアノテーション取得
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			ScreenTrans screenTrans = handlerMethod.getMethodAnnotation(ScreenTrans.class);
			//System.out.println(handlerMethod.getMethod().toString());
			//System.out.println(screenTrans);
			
			//	リファラー取得
			if(screenTrans  != null && screenTrans.referer() != null){
				String referer = request.getHeader("referer");
				//System.out.println("path= " + referer + " , patern=" + screenTrans.referer());
				
				if(!screenTrans.referer().equals("")){
					Pattern p = Pattern.compile(screenTrans.referer().trim());
					Matcher m = p.matcher(referer);
					
					//	マッチしない場合はエラー
					if(m.find() == false){
						throw new Exception("Path is different according to refer in ScreenTrans");
					}
				}
			}
			
		}
		
		//	CSEF判定
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
	    final HttpServletResponse httpResponse = (HttpServletResponse) response;
	      
		boolean error = false;
		//System.out.println(httpRequest.getMethod());
		if (httpRequest.getMethod().startsWith("POST")) {
			String CSRFKey = "";
			String CSRFVal = "";
			httpRequest.getParameterNames();
			Enumeration e1 = httpRequest.getParameterNames();
		    while (e1.hasMoreElements()) {
		    	String name = (String) e1.nextElement();
		    	if(name.startsWith(Const.CRSF_PREFIX)){
		    		CSRFKey = name;
		    		CSRFVal = httpRequest.getParameterValues(CSRFKey)[0];
		    		error = true;
		    		break;
		    	}
		    }
		    
		    HttpSession httpSession = httpRequest.getSession();
		    Object object = httpSession.getAttribute(CSRFKey);
		    if(httpSession != null & object != null){
		    	//System.out.println(object.toString() +"=="+ CSRFVal);
			    if(object.toString().equals(CSRFVal)){
		    	error = false;
			    }
		    }
			
		}
		if(error){
			new Exception(ConstError.CRSF_ERR);
		}
		
		return true;
	}
	
	@Override
	public void postHandle(	HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
			if(modelAndView == null){
				return;
			}
			//System.out.println("---method executed---");
			
			ModelMap modelmap = modelAndView.getModelMap();
			
			//String md5key = DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()) + String.valueOf(Math.random()));
			//modelAndView.addObject("token1_name","k_"+ md5key);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
		Object handler, Exception ex) throws Exception {
		
		//System.out.println("---Request Completed---");
		Collection<String> heders = response.getHeaderNames();
		for(String headerName: heders){
			//System.out.println(headerName);
		}
		
	}
}

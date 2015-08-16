package demo.controller;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import demo.annotation.ScreenTrans;


public class ControllerHandler implements HandlerInterceptor  {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		System.out.println("---Before Method Execution---");
		//super.preHandle(request, response,handler);
		
		if(handler instanceof HandlerMethod){
			//	メソッドのアノテーション取得
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			ScreenTrans screenTrans = handlerMethod.getMethodAnnotation(ScreenTrans.class);
			
			//	リファラー取得
			String referer = request.getHeader("referer");
			System.out.println("path= " + referer + " , patern=" + screenTrans.referer());
			
			if(!screenTrans.referer().equals("")){
				Pattern p = Pattern.compile(screenTrans.referer().trim());
				Matcher m = p.matcher(referer);
				
				/*Pattern p = Pattern.compile(screenTrans.referer());
				Matcher m = p.matcher(referer);
				*/
				//	マッチしない場合はエラー
				if(m.find() == false){
					throw new Exception("Path is different according to refer in ScreenTrans");
				}
			}
			
			//	tokenチェック
			if(screenTrans.usetoken()){
				
			}
			
		}

		return true;
	}
	
	@Override
	public void postHandle(	HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
			System.out.println("---method executed---");
			
			ModelMap modelmap = modelAndView.getModelMap();
			System.out.println("always aaa");
			
			//String md5key = DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()) + String.valueOf(Math.random()));
			//modelAndView.addObject("token1_name","k_"+ md5key);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
		Object handler, Exception ex) throws Exception {
		
		System.out.println("---Request Completed---");
		Collection<String> heders = response.getHeaderNames();
		for(String headerName: heders){
			System.out.println(headerName);
		}
		
	}
}

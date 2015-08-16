package demo.configs;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import utils.transition.ScreenTrans;

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
		}

		return true;
	}
	
	@Override
	public void postHandle(	HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
			System.out.println("---method executed---");
			
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
		System.out.println("---Request Completed---");
		
		String hello = "sdafafsdaf";

		
	}
}

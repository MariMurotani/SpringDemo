package demo.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.StaticWebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@Controller
@RequestMapping("/itplan")
public class ITPlanController {
	@Autowired
	private ApplicationContext webapplicationContext;
	
	//@Autowired
	//private RequestMappingHandlerMapping handlerMapping;
	
	//private final RequestMappingHandlerMapping handlerMapping;

	//@Autowired
	//public ITPlanController(RequestMappingHandlerMapping handlerMapping) {
	//this.handlerMapping = handlerMapping;
	//}
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	class ResponseRowFormat{
		public String httpMethod = "";
		public String httpURL = "";
		public String classMethodName = "";
		public ArrayList httpParams = new ArrayList();
	}
	
	
	/**
	 * http://localhost:8080/itplan/index
	 * */
	@RequestMapping(value="index",method=RequestMethod.GET)
    public String index(Model model) {
		
		//	RequstMappingをしてるクラス一覧
		Map<String,Object> beans = webapplicationContext.getBeansWithAnnotation(RequestMapping.class);
		for(Map.Entry<String, Object> entry : beans.entrySet()){
			System.out.println(entry.toString());
			//this.getMethodInformation(entry.getClass());
		}
		
		//	全てのBean取得
		model.addAttribute("tree", "");
		String[] beanNames = webapplicationContext.getBeanDefinitionNames();
		System.out.println(beanNames);
		model.addAttribute("tree", beanNames);
		
		//  RequestMappingHandlerMappingを取得
		//RequestMappingHandlerMapping requestMappingHandlerMapping = webapplicationContext.getBean(RequestMappingHandlerMapping.class);
		//	Methodを取得
		//Map<RequestMappingInfo, HandlerMethod> methods= mapping.getHandlerMethods();
		String result = "sdfsafaf";
		for (RequestMappingInfo element : requestMappingHandlerMapping.getHandlerMethods().keySet()) {
			/*System.out.println("pettern: "+ element.getPatternsCondition());
			System.out.println("method: " + element.getMethodsCondition());
			System.out.println("params:" + element.getParamsCondition());
			System.out.println("consume:" + element.getConsumesCondition());
			System.out.println("header:" + element.getHeadersCondition());
			System.out.println("procedure:" + element.getProducesCondition());*/
			
	    }
		
		//	get all information related request mapping
		ArrayList myresult = new ArrayList();
		for(Entry<RequestMappingInfo, HandlerMethod> item : requestMappingHandlerMapping.getHandlerMethods().entrySet()) {
			ResponseRowFormat responseRowFormat = new ResponseRowFormat();
			
			RequestMappingInfo mapping = item.getKey();
			HandlerMethod method = item.getValue();
			MethodParameter[] methodParameter= method.getMethodParameters();
			
			for (String urlPattern : mapping.getPatternsCondition().getPatterns()) {
				String urlp = method.getBeanType().getName() + "#" + method.getMethod().getName() + " <-- " + urlPattern;
				System.out.println(urlp);
				responseRowFormat.classMethodName = urlp;
			}
			for (RequestMethod httpMethod : mapping.getMethodsCondition().getMethods()) {
				System.out.println(httpMethod);
				responseRowFormat.httpMethod  = httpMethod.toString(); 
			}
		
			for(int i = 0 ; i < methodParameter.length ; i++){
				MethodParameter parai = methodParameter[i];
				RequestParam reuquestParam = (RequestParam)parai.getParameterAnnotation(RequestParam.class);
				try{
					System.out.println("  value: " + reuquestParam.value()+ " , required: " + reuquestParam.required() + " , defaultValue: " + reuquestParam.defaultValue()) ;
					//responseRowFormat.classMethodName = urlp;
				}catch(NullPointerException nulp){
					
				}
			}	
			
			myresult.add(responseRowFormat);
			
		}       
		
		System.out.println(result);
		model.addAttribute("tree", result);
		model.addAttribute("handlerMethods",myresult);
		
		return "itplan/index";
	}
	
	private void getMethodInformation(Class cls){
		if(cls != null){
	        for(Method m :cls.getMethods()){
	        	System.out.println(m.getName());
	        	m.getReturnType();
	        	
	        	if(m.getName()=="Your Method"){
	                /*Annotation cc = (Annotation) m.getAnnotation(RequestMapping.class);
	                if(cc != null){
		                RequestMapping rm = (RequestMapping)cc;
		                for(String s:rm.value()){
		                    System.out.println(s);
		                }
	                }*/
	            }
	        }
		}
	}
	
}

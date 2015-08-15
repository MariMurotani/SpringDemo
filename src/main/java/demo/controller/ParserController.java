package demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.lang.Class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import demo.libs.EnglishParser;
import utils.transition.ScreenTrans;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

@EnableAutoConfiguration
@EnableConfigurationProperties
@Controller
@RequestMapping("/parse")
public class ParserController {
	@Autowired
	private ApplicationContext context;
	
	 /*@InitBinder
	 public void initBinder(WebDataBinder binder) {
		 System.out.println("Init binder start");
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	     dateFormat.setLenient(false);
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	} */
	 
	private void refereCheck(){
		//context.getBeanNamesForAnnotation(ScreenTrans.class);
		Map<String,Object> beans = context.getBeansWithAnnotation(
				RequestMapping.class);

		AnnotatedType[] types = this.getClass().getAnnotatedInterfaces();
		for(int i = 0 ; i < types.length ; i++){
			System.out.println(types[i].toString());
		}
		Annotation[] anos = (Annotation[]) this.getClass().getAnnotations();
		for(int g = 0 ; g < anos.length ; g++){
			System.out.println(anos[g].toString());
		}		
		for(Map.Entry<String, Object> entry : beans.entrySet()){
			System.out.println(entry.toString());
			//this.getMethodInformation(entry.getClass());
		}
		
		Method m;
		try {
			Class cls = this.getClass();
			System.out.println(new Throwable().getStackTrace()[0].getMethodName());
			m = (Method)this.getClass().getMethod(Thread.currentThread().getStackTrace()[0].getMethodName(),new Class[]{String.class});
			Annotation[] anos3 = m.getAnnotations();
			for(int k = 0 ; k < anos3.length ; k++){
				System.out.println(anos3[k]);
			}
			
			//System.out.println(m.invoke(hoge, new Object[0]));
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	/**
	 * http://localhost:8080/parse/index.html
	 * */
	@ScreenTrans(usetoken=true)
	@RequestMapping(value="index",method=RequestMethod.GET)
    public String index(Model model) {
		model.addAttribute("tree", "");
		//this.refereCheck();
		  
		/*dump("クラス", clazz.getDeclaredAnnotations());

		Annotation[][] ma = ms[0].getParameterAnnotations();
		dump("引数1", ma[0]);
		dump("引数2", ma[1]);
		*/
		return "parse/index";
	}
	
	//,@CookieValue(value="SESSION",defaultValue="") String cookie, @RequestHeader("Keep-Alive") long keepAlive
	@ScreenTrans(referer="/parse/index")
	@RequestMapping(value="doParse",method=RequestMethod.POST)
	public String doParse(@RequestParam(value="text", required=true) String value,Model model){
		//System.out.println(cookie);
		//System.out.println(keepAlive);
		model.addAttribute("tree", "");
		EnglishParser mp = new EnglishParser();
		model.addAttribute("tree", mp.doParse(value));
		
		return "parse/index";
	}
	
	@RequestMapping(value="test",method=RequestMethod.POST)
	public String doTest(){
	
		return "parse/index";
	}		
}

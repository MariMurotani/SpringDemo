package demo.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.servlet.ServletContext;

import org.apache.catalina.Server;
import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import demo.annotation.ScreenTrans;
import demo.libs.EnglishParser;
import demo.libs.EnglishParser.MyCallback;

@EnableAutoConfiguration
@EnableConfigurationProperties
@Controller
@RequestMapping("/parse")
public class ParserController {
	@Autowired
	private ApplicationContext context;
	
	@Autowired
    private MessageSource messageSource;
	
	/**
	 * preExecuter in this class
	 * */
	 @InitBinder
	 public void initBinder(WebDataBinder binder) {
		 System.out.println("Init binder start in controller class");
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	     dateFormat.setLenient(false);
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	 } 

	
	/**
	 * http://localhost:8080/parse/index.html
	 * */
	String resultValue = "";
	@ScreenTrans(usetoken=true)
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(Model model) {
		System.out.println(LocaleContextHolder.getLocale());
		String message = messageSource.getMessage("user.welcome",new String[]{"Mari"}, LocaleContextHolder.getLocale());
		System.out.println(message);

		model.addAttribute("userName","Mari");
		model.addAttribute("tree", "");
		return "parse/index";
	}
	
	//,@CookieValue(value="SESSION",defaultValue="") String cookie, @RequestHeader("Keep-Alive") long keepAlive
	@ScreenTrans(referer="/parse/index|/parse/doParse")
	@RequestMapping(value="doParse",method=RequestMethod.POST)
	public String doParse(@RequestParam(value="text", required=true) String value,Model model){
		model.addAttribute("tree", "");
		
		//synchronized(this){
			resultValue = "";
			EnglishParser mp = new EnglishParser();
			mp.setText(value);
			mp.setCallback(new MyCallback(){
				@Override
				public void callbackCall(String text) {
					resultValue = text;
				}
			});
			mp.start();
			
	        try {
				for (int i = 0; i < 30; i++) {
		            //Pause for 0.3 seconds
					Thread.sleep(100);
		            if(resultValue.equals("") == false){
						model.addAttribute("tree", resultValue);
		    			return "parse/index";
		            }
		         }
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		return "parse/timeout";
	}
	
	@RequestMapping(value="test",method=RequestMethod.GET)
	public String doTest(){
		return "parse/index";
	}		
}

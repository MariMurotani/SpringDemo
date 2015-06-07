package demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@EnableAutoConfiguration
@EnableConfigurationProperties
@Controller
@WebServlet("/session")
public class HelloWorldController {
	
	@Autowired
	private ApplicationContext context;
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/api",method=RequestMethod.GET)
    public @ResponseBody Greeting apiWithJson(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
        
    }
    
    @RequestMapping(value="/html",method=RequestMethod.GET)
    public String pageWithHtml(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model,HttpServletRequest request,HttpServletResponse response) {
    	
    	Integer sessValue = (Integer) request.getSession().getAttribute("sessionCounter");
    	int sessionCounter = 0;
        if(sessValue != null) {
        	sessionCounter = Integer.valueOf(sessValue)+1;
        }
        request.getSession().setAttribute("sessionCounter", sessionCounter);
        
        String sessID = "";
        if(request.isRequestedSessionIdFromCookie()){
        	sessID = request.getSession().getId().toString();
        }
    	
    	model.addAttribute("name", name);
    	model.addAttribute("SSID", sessID);
    	model.addAttribute("sessionCounter", sessionCounter);
    	return "page";
        
    }

}
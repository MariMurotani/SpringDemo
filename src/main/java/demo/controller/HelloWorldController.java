package demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloWorldController {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/api",method=RequestMethod.GET)
    public @ResponseBody Greeting apiWithJson(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
        
    }
    
    @RequestMapping(value="/html",method=RequestMethod.GET)
    public String pageWithHtml(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
    	model.addAttribute("name", name);
    	return "page";
        
    }

}
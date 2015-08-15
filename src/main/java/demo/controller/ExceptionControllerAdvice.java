package demo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@InitBinder
    public ModelAndView init(Exception e) {
         
        ModelAndView mav = new ModelAndView("exception");
        mav.addObject("name", e.getClass().getSimpleName());
        mav.addObject("message", e.getMessage());
 
        return mav;
    }
    
	@ExceptionHandler(Exception.class)
    public String exception(Exception e) {
		System.out.println(e.getMessage());
        return "error";
    }
}

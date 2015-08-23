package demo.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;


//@ControllerAdvice
public class ExceptionControllerAdvice {

	 @InitBinder
	 public void initBinder(WebDataBinder binder) {
		System.out.println("controller advice: init binder");
    }
	
    
	@ExceptionHandler(Exception.class)
    public String exception(Exception e) {
		System.out.println("controller advice: exception Handler");
		System.out.println(e.getMessage());
        return "error";
    }
	
	@ModelAttribute
	public void modelAttribute(){
		System.out.println("controller advice:model Attribute");
	}
}

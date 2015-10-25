package demo.thymeleaf;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import demo.configs.ConstError;
import demo.service.MyMessageService;

@Component
public class MyMessageSource implements MessageSource{
	@Autowired
	MyMessageService messageService;

	@Autowired
	MessageSource messageSource;
	
	@Override
	public String getMessage(String code, Object[] args,String defaultMessage, Locale locale) {
		return this.getMessage(code,args,"",locale);
	}
	
	@Override
	public String getMessage(String code, Object[] args, Locale locale) {
		String sMess = messageService.GetMessage(code, locale.toString());
		
		if(sMess == null | sMess == ""){
			sMess = messageSource.getMessage(code,args,locale) ;
		}
		if(sMess == null){
			sMess = ConstError.MESSAGE_NON_EXIST_ERR;
		}
		
		return sMess;
	}
	
	@Override
	public String getMessage(MessageSourceResolvable resolvable, Locale locale) {
		// TODO Auto-generated method stub
		String sMess = messageSource.getMessage(resolvable,locale);
		return sMess;
	}

}

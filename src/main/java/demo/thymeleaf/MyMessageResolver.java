package demo.thymeleaf;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import org.thymeleaf.Arguments;
import org.thymeleaf.messageresolver.MessageResolution;
import org.thymeleaf.messageresolver.StandardMessageResolver;

import com.mongodb.Mongo;

import demo.mongo.service.MyMessageService;


public class MyMessageResolver extends StandardMessageResolver implements MessageSource{

	private MessageSource messageSource;
	
	@Autowired
	protected MongoOperations mongoOperation;
	

	/**
	 *  for MessageSource
	 * */
	public void setMessageSource(MessageSource messageSource) {
	    this.messageSource = messageSource;
	}
	
	
	@Override
	public String getMessage(String code, Object[] args,String defaultMessage, Locale locale) {
		return this.getMessage(code,args,"",locale);
	}
	
	@Override
	public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
		
		MyMessageService messageService = new MyMessageService();
		String sMess = messageService.GetMessage(code, locale.toString());
		
		if(sMess == null){
			sMess = messageSource.getMessage(code,args,locale);
		}
		
		
		return sMess;
	}
	
	@Override
	public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
		// TODO Auto-generated method stub
		String sMess = messageSource.getMessage(resolvable,locale);
		return sMess;
	}
	
	/**
	 * for StandardMessageResolver
	 * 
	 * */
	@Override
	public MessageResolution resolveMessage(Arguments arguments, String key,Object[] messageParameters) {
		// TODO Auto-generated method stub
		System.out.println(key.toString());
		System.out.println(LocaleContextHolder.getLocale().toString());
		
		String sMess = this.getMessage(key.toString(), null,LocaleContextHolder.getLocale());
		
		MessageResolution resolution = null; 
		if(sMess == null){ 
			resolution = super.resolveMessage(arguments, key, messageParameters);	 
		}
			
		return resolution;
	}
}

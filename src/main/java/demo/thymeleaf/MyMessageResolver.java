package demo.thymeleaf;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.Arguments;
import org.thymeleaf.messageresolver.MessageResolution;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.standard.StandardMessageResolutionUtils;

import demo.configs.ConstError;
import demo.mongo.service.MyMessageService;


@Component
public class MyMessageResolver extends StandardMessageResolver {

	@Autowired
	MyMessageSource messageSource;
		
	@Autowired
	ApplicationContext context;
	
	public MyMessageResolver(){
		
	}
	
	
	/**
	 *  for MessageSource
	 * */
	public void setMessageSource(MyMessageSource messageSource) {
	    this.messageSource = messageSource;
	}
	
	
	/**
	 * for StandardMessageResolver
	 * 
	 * */
	@Override
	public MessageResolution resolveMessage(Arguments arguments, String key,Object[] messageParameters) {
		System.out.println(key.toString());
		System.out.println(LocaleContextHolder.getLocale().toString());
		
		//String sMess = this.getMessage(key.toString(), null,LocaleContextHolder.getLocale());
		String sMess = this.messageSource.getMessage(key.toString(), null,LocaleContextHolder.getLocale());
		super.checkInitialized();
		MessageResolution resolution = new MessageResolution(sMess);
		
		return resolution;
	}
}

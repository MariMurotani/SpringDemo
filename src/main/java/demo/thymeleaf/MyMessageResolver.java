package demo.thymeleaf;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.thymeleaf.TemplateProcessingParameters;
import org.thymeleaf.TemplateRepository;
import org.thymeleaf.context.DialectAwareProcessingContext;
import org.thymeleaf.context.VariablesMap;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.messageresolver.AbstractMessageResolver;
import org.thymeleaf.messageresolver.MessageResolution;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.standard.StandardMessageResolutionUtils;

import demo.configs.ConstError;
import demo.mongo.service.MyMessageService;


@Component
public class MyMessageResolver extends AbstractMessageResolver {//StandardMessageResolver

	@Autowired
	MyMessageSource messageSource;
		
	@Autowired
	ApplicationContext context;
	
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
		String sMess = this.messageSource.getMessage(key.toString(), null,LocaleContextHolder.getLocale());
		//super.checkInitialized();
		
		if(!(messageParameters[0] instanceof String)){
			
			for(int i = 0; i < ((Object[])messageParameters[0]).length ; i++){
				sMess = sMess.replace("{" +String.valueOf(i)+ "}",String.valueOf(((Object[])messageParameters[0])[i]));
			}
		}else{
			for(int i = 0; i < messageParameters.length ; i++){
				System.out.println(messageParameters[i]);
				sMess = sMess.replace("{" +String.valueOf(i)+ "}",String.valueOf(messageParameters[i]));
			}
		}
		MessageResolution resolution = new MessageResolution(sMess);
		return resolution;
	}
}

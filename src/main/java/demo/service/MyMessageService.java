package demo.service;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import demo.configs.SpringMongoConfig;
import demo.model.Message;
import demo.model.User;

@Service
public class MyMessageService extends ServiceBase {
	
	public MyMessageService() {
		super();
	}
	
	 public String GetMessage(String code,String locale){
		String result = "";
		 
		Query searchUserQuery = this.GetSaerchQuery(code, locale);
		List<Message> messages = mongoOperation.find(searchUserQuery, Message.class);
		Iterator iterator = messages.iterator();
		while(iterator.hasNext()) {
			Message message = (Message)iterator.next();
			String localereg = message.getLocale();
			if(localereg != null && localereg  != ""){
				Pattern p = Pattern.compile(localereg);
				Matcher m = p.matcher(locale);
				if(m.matches()){
					result = message.getValue() == null?result:message.getValue();
					break;
				}
			}
		}
		
		return result;
    }
	 
    private Query GetSaerchQuery(String code,String locale){
		//Query searchMessageQuery = new Query(Criteria.where("code").is(code).andOperator(Criteria.where("locale").regex(locale + ".*")));
		//Query searchMessageQuery = new Query(Criteria.where("code").is(code).andOperator(Criteria.where(locale).regex("locale")));
    	Query searchMessageQuery = new Query(Criteria.where("code").is(code));
    	searchMessageQuery.with(new Sort(Sort.Direction.DESC, "update_time"));
			
		System.out.println(searchMessageQuery.toString());;
		return searchMessageQuery;
    	
    }
}

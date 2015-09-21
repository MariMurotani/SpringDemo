package demo.mongo.service;

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

import demo.configs.SpringMongoConfig;
import demo.mongo.model.Message;
import demo.mongo.model.User;

@Service
public class MyMessageService extends ServiceBase {
	
	public MyMessageService() {
		super();
		
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);  
		//MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");  
		
	}
	
	 public String GetMessage(String code,String locale){
		
		System.out.println(this.mongoOperation.toString());
		 
		Query searchUserQuery = this.GetSaerchQuery(code, locale);
		Message message = mongoOperation.findOne(searchUserQuery, Message.class);
		String result = message.getValue();
		return result;
    }
	 
    private Query GetSaerchQuery(String code,String locale){
		Query searchMessageQuery = new Query(Criteria.where("code").is(code).andOperator(Criteria.where("locale").regex(locale + ".*")));
		searchMessageQuery.with(new Sort(Sort.Direction.DESC, "update_time"));
		
		System.out.println(searchMessageQuery.toString());;
		return searchMessageQuery;
    	
    }
}

package demo;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import demo.mongo.config.SpringMongoConfig;
import demo.mongo.model.User;


/**
 * Sample using CommandLineRunner and Mongo DB
 * http://qiita.com/niwashun/items/c52e890180754bf0bc25
 * 
 * */
@SpringBootApplication
public class MongExample implements CommandLineRunner {
	
	//	get Applicateion context Example2
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private Environment env;

	
	/*@Autowired
    private MongoTemplate mongoTemplate;

    public void MyBean(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }*/
    
	public static void main(String[] args) {
		SpringApplication.run(MongExample.class, args);
	}
   
	@Override
	public void run(String... args) throws Exception {
		
		//	get mongo Template
		MongoOperations mongoOperation = (MongoOperations) context.getBean("mongoTemplate");
		
		//	Data Object this structure can be a table
		User user = new User("mkyong", "password123");

		// save
		mongoOperation.save(user);

		// now user object got the created id.
		System.out.println("1. user : " + user);

		// query to search user
		Query searchUserQuery = new Query(Criteria.where("username").is("mkyong"));

		// find the saved user again.
		User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
		System.out.println("2. find - savedUser : " + savedUser);

		// update password
		mongoOperation.updateFirst(searchUserQuery, 
	                        Update.update("password", "new password"),User.class);

		// find the updated user object
		User updatedUser = mongoOperation.findOne(searchUserQuery, User.class);

		System.out.println("3. updatedUser : " + updatedUser);

		// delete
		//mongoOperation.remove(searchUserQuery, User.class);

		// List, it should be empty now.
		List<User> listUser = mongoOperation.findAll(User.class);
		System.out.println("4. Number of user = " + listUser.size());
		
		
	}
}
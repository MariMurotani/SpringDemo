package demo;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.*;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import demo.mongo.model.User;



/**
 * Sample using CommandLineRunner and Mongo DB
 * http://qiita.com/niwashun/items/c52e890180754bf0bc25
 * 
 * */
@EnableAutoConfiguration
@EnableBatchProcessing
@EnableConfigurationProperties
public class MongExample implements CommandLineRunner {
	
	//	get Applicateion context Example2
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private Environment env;

	public static void main(String[] args) {
		//SpringApplication.run(MongExample.class, args);
		
		SpringApplication application = new SpringApplication(MongExample.class);
		application.setWebEnvironment(false);
		ApplicationContext context = application.run();
		SpringApplication.exit(context);
		
		
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
package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.apache.hadoop.fs.Path;
import org.apache.mahout.classifier.ClassifierResult;
import org.apache.mahout.common.nlp.NGrams;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.List;

import demo.model.User;



/**
 * Sample using CommandLineRunner and Mongo DB
 * http://qiita.com/niwashun/items/c52e890180754bf0bc25
 * 
 * */
@EnableAutoConfiguration
@EnableBatchProcessing
@EnableConfigurationProperties
public class Mahaut implements CommandLineRunner {
	
	//	get Applicateion context Example2
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private Environment env;

	public static void main(String[] args) {
		//SpringApplication.run(MongExample.class, args);
		
		SpringApplication application = new SpringApplication(Mahaut.class);
		application.setWebEnvironment(false);
		ApplicationContext context = application.run();
		SpringApplication.exit(context);
	}
   
	@Override
	public void run(String... args) throws Exception {
	}
}
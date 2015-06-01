package demo.mongo.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import config.ExternalizedConfig;
import config.ExtraConfig;
import config.RelaxedConfig;

@Configuration
@EnableMongoRepositories("demo.mongo.model")
public class SpringMongoConfig extends AbstractMongoConfiguration {
	@Value("${spring.profiles.active}")
	private String profileActive;

	@Value("${spring.application.name}")
	private String proAppName;

	@Value("${spring.data.mongodb.host}")
	private String mongoHost;
	
	@Value("${spring.data.mongodb.port}")
	private String mongoPort;
	
	@Value("${spring.data.mongodb.database}")
	private String mongoDB;
	
	@Override
	public MongoMappingContext mongoMappingContext()
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.mongoMappingContext();
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		System.out.println("start " + profileActive + "environment, " + proAppName + "started");
		System.out.println("mongo host: "+ mongoHost);
		System.out.println("mongo db: "+ mongoDB);
		return new MongoClient(mongoHost + ":" + mongoPort);
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return mongoDB;
	}
	
    /*@Override
    protected String getMappingBasePackage()
    {
        return "demo.mongo.model";
    }*/
}
package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * http://www.mkyong.com/mongodb/spring-data-mongodb-query-document/
 * */
public class ServiceBase {
	@Autowired
	protected MongoOperations mongoOperation;

	public ServiceBase() {
		
	}
}

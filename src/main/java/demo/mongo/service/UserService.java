package demo.mongo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import demo.mongo.model.User;

@Service
public class UserService extends ServiceBase{


	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}
		/**
		 *Query query = new Query(Criteria.where('tagName').is('water temperature'));
		query.sort().on('timestamp', Order.DESCENDING);
 * */
	/*@Autowired
    public UserService(MongoOperations operations) {

      //Assert.notNull(operations, "MongoOperations must not be null!");
      this.mongoOperation = operations;
    }*/
	
	public void AddNewUser(String name, String pass) {
		User user = new User(name,pass);
		this.mongoOperation.save(user);
	}
	
	public boolean IsExists(String username,String passwd){
		Query searchUserQuery = this.GetSaerchQuery(username, passwd);
		Long lres = this.mongoOperation.count(searchUserQuery,User.class);
		if(lres > 0){
			return true;
		}
    	return false;
    }
    
    public User GetUserInfo(String username,String passwd){
		Query searchUserQuery = this.GetSaerchQuery(username, passwd);
		User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
		return savedUser;
    }
    
    private Query GetSaerchQuery(String username,String passwd){
		Query searchUserQuery = new Query(Criteria.where("username").is(username).andOperator(Criteria.where("password").is(passwd)));
		System.out.println(searchUserQuery.toString());;
		return searchUserQuery;
    	
    }
    
    public List<User> FindAll(int pos,int limit){
		Query searchUserQuery = new Query().skip(pos).limit(limit);
		System.out.println(searchUserQuery.toString());
		
    	List<User> users = mongoOperation.find(searchUserQuery,User.class);
		return users;
    	
    }
}
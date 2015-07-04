package demo.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.dto.Greeting;
import demo.dto.Status;
import demo.mongo.model.User;
import demo.mongo.service.UserService;


/**
 * http://stackoverflow.com/questions/17008947/spring-data-mongotemplate-vs-mongouserService
 * */
@EnableAutoConfiguration
@EnableConfigurationProperties
@Controller
@RequestMapping("/mongo")
public class MongoDBSampleWeb {
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private UserService userService;

	private final AtomicLong counter = new AtomicLong();
	
	/**
	 * curl -d name="murotani" -d pass="test" http://localhost:8080/mongo/addUser
	 * */
	@RequestMapping(value="addUser",method=RequestMethod.POST)
    public @ResponseBody Status mogoAddUser(@RequestParam(value="name", required=true, defaultValue="Stranger") String name,@RequestParam(value="pass", defaultValue="000000") String pass) {
		try{
			userService.AddNewUser(name,pass);
			return new Status("OK");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}catch(Error e){
			System.out.println(e.getMessage());
		}
		return new Status("NG");
	}
	
	/**
	 * curl -d name="murotani" -d pass="test" http://localhost:8080/mongo/isExists
	 */
	@RequestMapping(value="isExists",method=RequestMethod.POST)
    public @ResponseBody Status mogoDeleteUser(@RequestParam(value="name", required=true) String name,@RequestParam(value="pass", required=true) String pass) {
		try{
			boolean res = userService.IsExists(name,pass);
			if(res = true){
				return new Status("OK");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}catch(Error e){
			System.out.println(e.getMessage());
		}
		return new Status("NG");
	}

	/**
	 * curl -d name="murotani" -d pass="test" http://localhost:8080/mongo/getUserInfo
	 */
	@RequestMapping(value="getUserInfo",method=RequestMethod.POST)
    public @ResponseBody User mogoGetUserInfo(@RequestParam(value="name") String name,@RequestParam(value="pass") String pass) {
		try{
			User res = userService.GetUserInfo(name, pass);
			//ResponseUser resuser = new ResponseUser(res.getId(),res.getPass());
			//ResponseUser resuser = new ResponseUser("id","test","pass");
			return res;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}catch(Error e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * curl -d pos="3" -d limit="5" http://localhost:8080/mongo/findAllUser
	 */
	@RequestMapping(value="findAllUser",method=RequestMethod.POST)
    public @ResponseBody List<User> findUser(@RequestParam(value="pos", required=true, defaultValue="0") int pos,@RequestParam(value="limit", required=true, defaultValue="10") int limit) {
		List<User> users = this.userService.FindAll(pos,limit);
		System.out.println(users.size());
		return users;
    }
    
	/*@RequestMapping(value="deleteUser",method=RequestMethod.POST)
    public @ResponseBody Status mogoDeleteUser(@RequestParam(value="id", required=true) String name) {
	userserv.delete(id);
		return new Status();
    }*/
	
	
}

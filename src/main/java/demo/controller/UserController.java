package demo.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.dto.AddedKey;
import demo.dto.Status;
import demo.model.CMS;
import demo.model.PathList;
import demo.service.CMSService;
import demo.service.UserService;

@EnableAutoConfiguration
@EnableConfigurationProperties
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private UserService userService;

	private final AtomicLong counter = new AtomicLong();
	
	/**
	 * curl -d mail="muromari5123@gmail.com" -d pass="pass" http://localhost:8080/user/addData
	 * @throws Exception 
	 * */
	@RequestMapping(value="addData",method=RequestMethod.POST)
    public @ResponseBody AddedKey cmsAddData(@RequestParam(value="mail", required=true, defaultValue="Stranger") String mail,@RequestParam(value="pass", defaultValue="") String pass) throws Exception {
		String value = userService.AddNewUser(mail, pass);
		AddedKey adk = new AddedKey(value);
		return adk;
	}
	
	/**
	 * curl -d mail="muromari5123@gmail.com" -d pass="pass" http://localhost:8080/user/startAuth
	 * */
	@RequestMapping(value="startAuth",method=RequestMethod.POST)
    public @ResponseBody Status cmsStartAuth(@RequestParam(value="mail", required=true, defaultValue="Stranger") String mail,@RequestParam(value="pass", defaultValue="") String pass) {
		Boolean value = userService.IsExists(mail, pass);
		Status sts = new Status(value);
		return sts;
	}
}
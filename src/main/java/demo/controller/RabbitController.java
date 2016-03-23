package demo.controller;


import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.configs.Const;
import demo.dto.Mail;
import demo.dto.Status;

@EnableAutoConfiguration
@EnableConfigurationProperties
@Controller
@RequestMapping(value="/rabiit",method=RequestMethod.POST)
public class RabbitController {
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	/**
	 * curl -d to="muromari@com" -d text="1いいねしました" http://localhost:8080/rabiit/push
	 * curl -d to="muromari@com" -d text="3写真アップしました" http://localhost:8080/rabiit/push
	 * */
	@RequestMapping(value="/push",method=RequestMethod.POST)
    public @ResponseBody Status getAllPath(@RequestParam(value="to", required=true) String to,@RequestParam(value="text", defaultValue="") String text) {
		ObjectMapper mapper = new ObjectMapper();
		
		Mail mail = new Mail();
		mail.setToAddress(to);
		mail.setStrContent(text);
		
		try {
			String jsonInString = mapper.writeValueAsString(mail);
			rabbitTemplate.convertAndSend(Const.RabbitMQMessageQue,jsonInString);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Status("NG");
		}
		
		return new Status("OK");
		
	}
}

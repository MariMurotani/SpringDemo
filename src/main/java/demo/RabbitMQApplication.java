package demo;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import demo.configs.Const;
import demo.dto.Mail;

@EnableAutoConfiguration
@EnableBatchProcessing
@EnableConfigurationProperties
public class RabbitMQApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext context;
	
	
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Bean
	Queue queue() {
		return new Queue(Const.RabbitMQMessageQue, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(Const.RabbitMQMessageQue);
	}

	@Bean
	CachingConnectionFactory rabbitConnectionFactory(){
		CachingConnectionFactory rabicon = new CachingConnectionFactory();
		//amqp.connect('amqp://admin:admin@192.168.3.3:5672/',
		rabicon.setHost(Const.Rabbit_Host);
		rabicon.setUsername(Const.Rabbit_User);
		rabicon.setPassword(Const.Rabbit_Pass);
		rabicon.setPort(5672);
		return rabicon;
	}


	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(Const.RabbitMQMessageQue);
		//container.setMessageListener(listenerAdapter);
		return container;
	}
	
	/*
	For asyncronized receiving
	
	@Bean
    Receiver receiver() {
        return new Receiver();
    }

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}*/
	
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RabbitMQApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Waiting five seconds...");
       
        while(0 < 1){
        	for(int i = 0 ; i < 5 ; i++){
	        	String object = (String)rabbitTemplate.receiveAndConvert(Const.RabbitMQMessageQue);
	        	if(object != null){
	        		try{
	        			System.out.println(new Date().toGMTString() + ": " + object);
	        			ObjectMapper mapper = new ObjectMapper();
	        			Mail mail = mapper.readValue(object, Mail.class);
	        			System.out.println(mail.getToAddress() + " , " + mail.getStrContent());
	        		}catch(Exception e){
	        			System.out.println(e.getMessage());
	        		}
	        	}
        	}
        	Thread.sleep(10000);
        }
    }
}
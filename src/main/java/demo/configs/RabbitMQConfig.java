package demo.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import demo.service.Receiver;

@Configuration
public class RabbitMQConfig {

	/*@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}*/
	
	@Bean
	CachingConnectionFactory rabbitConnectionFactory(){
		CachingConnectionFactory rabicon = new CachingConnectionFactory();
		//amqp.connect('amqp://admin:admin@192.168.3.3:5672/',
		rabicon.setHost("192.168.3.3");
		rabicon.setUsername("admin");
		rabicon.setPassword("admin");
		rabicon.setPort(5672);
		return rabicon;
	}

	@Bean
	SimpleMessageListenerContainer simpleMessage(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(Const.RabbitMQMessageQue);
		//container.setMessageListener(listenerAdapter);
		return container;
	}
	
	
	
    /*
     * レシーバーは一旦コメントアウト
     * @Bean
    Receiver receiver() {
        return new Receiver();
    }

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}*/

}

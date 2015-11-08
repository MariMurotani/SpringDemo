package demo.configs;

import com.rabbitmq.client.ConnectionFactory;

public class MyRabbitConnectionFactory extends ConnectionFactory{

	
	public MyRabbitConnectionFactory() {
		super();
		// TODO Auto-generated constructor stub
		this.setHost("192.168.3.3");
		this.setUsername("guest");
		this.setPassword("guest");
	}

	@Override
	public String getHost() {
		// TODO Auto-generated method stub
		return "192.168.3.3";
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return "admin";
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return "admin";
	}

	@Override
	public int getPort() {
		// TODO Auto-generated method stub
		return 5672;
	}

	@Override
	public String getVirtualHost() {
		// TODO Auto-generated method stub
		return "/";
	}
	
	
	
}

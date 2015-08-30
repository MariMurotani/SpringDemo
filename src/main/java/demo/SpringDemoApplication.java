package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
    	
    	SpringApplication application = new SpringApplication(SpringDemoApplication.class);
		ApplicationContext context = application.run(args);

    }
}
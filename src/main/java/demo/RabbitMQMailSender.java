package demo;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class RabbitMQMailSender  implements CommandLineRunner{
	
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RabbitMQMailSender.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Thread.sleep(5000);
        
        //receiver().getLatch().await(10000, TimeUnit.MILLISECONDS);

    }

}

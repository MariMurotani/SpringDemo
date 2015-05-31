package demo;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoApplication {
	 //@Value("${spring.profiles.active}")
	 //private String activeProfile;

	 @Value("${spring.data.mongodb.host}")
	 private String strTest;

    public static void main(String[] args) {
    	SpringApplication.run(SpringDemoApplication.class, args);
    }
  
}
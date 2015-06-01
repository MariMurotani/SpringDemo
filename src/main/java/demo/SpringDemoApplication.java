package demo;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoApplication {
	 
    public static void main(String[] args) {
    	SpringApplication.run(SpringDemoApplication.class, args);
    }
  
}
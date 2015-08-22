package demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

import demo.thymeleaf.ThymeleafDialect;

@Configuration
public class ThymeleafConfiguration extends WebMvcConfigurerAdapter {
		
	
		// - ClassLoaderTemplateResolver 
		// - FileTemplateResolver 
		// - ServletContextTemplateResolver   not working
		// - UrlTemplateResolver 
	
		//	reading from file system
		/*@Bean
		public UrlTemplateResolver templateResolver() {
			UrlTemplateResolver urlTemplateResolver = new UrlTemplateResolver();
			urlTemplateResolver.setCacheable(false);
			urlTemplateResolver.setPrefix("file:/Users/murotanimari/templates/");
			urlTemplateResolver.setSuffix(".html");
			urlTemplateResolver.setTemplateMode("HTML5");
			urlTemplateResolver.setCacheable(false);
			return urlTemplateResolver;
		}*/
	
		@Bean
		public ClassLoaderTemplateResolver templateResolver() {
			ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
			classLoaderTemplateResolver.setCacheable(false);
			classLoaderTemplateResolver.setPrefix("templates/");
			classLoaderTemplateResolver.setSuffix(".html");
			classLoaderTemplateResolver.setTemplateMode("HTML5");
			classLoaderTemplateResolver.setCacheable(false);
			return classLoaderTemplateResolver;
		}
	
	
		/*
		 * Not working
		 * @Bean
	    public ServletContextTemplateResolver templateResolver() {
	        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
	        templateResolver.setPrefix("/src/main/resources/templates/");
	        templateResolver.setSuffix(".html");
	        templateResolver.setTemplateMode("HTML5");
	        templateResolver.setCacheable(false);
	        return templateResolver;
	    }*/
	    
	    @Bean
	    public SpringTemplateEngine templateEngine() {
	        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	        templateEngine.setTemplateResolver(templateResolver());
	        templateEngine.addDialect(new ThymeleafDialect());
	        return templateEngine;
	    }
	    
	    @Bean
	    public ThymeleafViewResolver viewResolver() {
	        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	        viewResolver.setTemplateEngine(templateEngine());
	        
	        return viewResolver;
	    }
	    
	    @Bean
	    @Description("Spring message resolver")
	    public ResourceBundleMessageSource messageSource() {  
	        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();  
	        messageSource.setBasename("i18n/messages");  
	        
	        return messageSource;  
	    }
	    
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	    }
}

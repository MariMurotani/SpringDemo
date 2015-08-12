package demo.configs;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import demo.webmvc.MyResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/////	↓↓↓↓　handler for screen transitio controll
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// TODO Auto-generated method stub
		
		/*<bean class="org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping">
    	<property name="order" value="0" />
    	<property name="useDefaultSuffixPattern" value="false" />
		</bean>*/
		
		super.configurePathMatch(configurer);
	}
		
	@Override
	public void addReturnValueHandlers(
			List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		// TODO Auto-generated method stub
		super.addReturnValueHandlers(returnValueHandlers);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		super.configureViewResolvers(registry);
	}
	/////	↑↑↑↑　handler for screen transitio controll
	
	
	
	
	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/img/**").addResourceLocations("resourceHandler");
	    //registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
	}*/
/*	
 * 
 * <bean name="resourceHandler" class="com.test.MyCustomResourceHttpRequestHandler">
    <property name="locations">
        <list>
            <!-- you'll be overriding this programmatically -->
            <value>/resources/</value>
        </list>
    </property>
</bean>

<bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
    <property name="urlMap">
        <map>
            <entry key="/resources/**" value-ref="resourceHandler"/>
        </map>
    </property>
</bean>
 * 
 * */
	
/*
	@Bean
	public MyCustomResourceHttpRequestHandler resourceHandler(){
		//MyCustomResourceHttpRequestHandler myCustomResourceHttpRequestHandler = new MyCustomResourceHttpRequestHandler();
		MyCustomResourceHttpRequestHandler list = new MyCustomResourceHttpRequestHandler();
		
		List<Resource> locations = new ArrayList<Resource>();
		locations.add(new ClassPathResource("/img/"));
		
		//Resource template = ctx.getResource("classpath:/img/");
		
        list.setLocations(locations);
		
		return list;
	}
	*/
	/*
    @Bean
    public SimpleUrlHandlerMapping sampleServletMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();

        Properties urlProperties = new Properties();
        urlProperties.put("/img/**", "resourceHandler");

        mapping.setMappings(urlProperties);

        return mapping;
    }*/
	
    
	/*
	 * 
	@Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        //templateResolver.setPrefix(VIEWS);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
		return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        //templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        return thymeleafViewResolver;
    }*/	
}

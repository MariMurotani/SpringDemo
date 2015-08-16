package demo.configs;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.Property;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import utils.transition.*;
import demo.webmvc.MyResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public MyTestBean myTestBean(){
		return new MyTestBean();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new ControllerHandler());
	    //TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
	}
	
	/*
	 * 
	 * 
	<bean id="handlerMapping"
            class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping">
        <property name="interceptors">
            <list>
                <ref bean="officeHoursInterceptor"/>
            </list>
        </property>
    </bean>
	 */	
	
	/*@Bean
	public RequestMappingHandlerMapping handlerMapping(){
		RequestMappingHandlerMapping rmm = new RequestMappingHandlerMapping();
		ArrayList<Property> pp = new ArrayList<Property>();
		//pp.add("name","interceptors");
		return rmm;
	}*/
	
	
	/*
    <bean id="officeHoursInterceptor"
            class="samples.TimeBasedAccessInterceptor">
        <property name="openingTime" value="9"/>
        <property name="closingTime" value="18"/>
    </bean>
	 * */
	
	/*@Bean
	public TimeBasedAccessInterceptor officeHoursInterceptor(){
		TimeBasedAccessInterceptor tbai = new TimeBasedAccessInterceptor();
		ArrayList<Property> pp = new ArrayList<Property>();
		pp.add(new Property(null, null, null));
		return tbai;
		
	}*/
	
	/***
	<bean name="resourceHandler" class="com.test.MyCustomResourceHttpRequestHandler">
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
		MyCustomResourceHttpRequestHandler list = new MyCustomResourceHttpRequestHandler();
		
		List<Resource> locations = new ArrayList<Resource>();
		locations.add(new ClassPathResource("/img/"));
		
		//Resource template = ctx.getResource("classpath:/img/");
		list.setLocations(locations);
		return list;
	}*/
	
	/*@Bean
	public SimpleUrlHandlerMapping sampleServletMapping() {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		
		Properties urlProperties = new Properties();

	    urlProperties.put("/img/**", "resourceHandler");
	    
	    mapping.setMappings(urlProperties);
	 return mapping;
	}*/

}

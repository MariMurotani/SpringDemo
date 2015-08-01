package demo.configs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.method.support.CompositeUriComponentsContributor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.util.UrlPathHelper;
import org.thymeleaf.spring4.SpringTemplateEngine;

//import org.thymeleaf.spring4.SpringTemplateEngine;
//import org.thymeleaf.spring4.view.ThymeleafViewResolver;

@Configuration
public class WebMvcConfigurerAdapter extends WebMvcConfigurationSupport{
	
	
	public WebMvcConfigurerAdapter() {
		super();
		SpringTemplateEngine templateEngine= new SpringTemplateEngine();
		System.out.println(templateEngine.toString());
	}
	
//    @Bean
//    public ThymeleafViewResolver thymeleafViewResolver() {
//        final ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setViewClass(ThymeleafTilesView.class);
//        resolver.setTemplateEngine(templateEngine);
//        resolver.setCharacterEncoding(UTF_8);
//        return resolver;
//    }


//	@Bean
//	public SimpleUrlHandlerMapping faviconHandlerMapping() {
//	    SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//	    mapping.setOrder(Integer.MIN_VALUE);
//	    mapping.setUrlMap(Collections.singletonMap("mylocation/favicon.ico",
//	            faviconRequestHandler()));
//	    return mapping;
//	}
//	    @Bean
//	    public ThymeleafTilesConfigurer tilesConfigurer() {
//	        final ThymeleafTilesConfigurer configurer = new ThymeleafTilesConfigurer();
//	        configurer.setDefinitions("classpath*:/templates/**/views.xml");
//	        return configurer;
//	    }
//
//	    @Bean
//	    public ThymeleafViewResolver thymeleafViewResolver() {
//	        final ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//	        resolver.setViewClass(ThymeleafTilesView.class);
//	        resolver.setTemplateEngine(templateEngine);
//	        resolver.setCharacterEncoding(UTF_8);
//	        return resolver;
//	    }
//
//	    @Bean
//	    public TilesDialect tilesDialect() {
//	        return new TilesDialect();
//	    }
//
//	    @Value("${server.session-timeout}") private Long sessionTimeOut;


}
package demo.configs;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

import demo.thymeleaf.CSRFFilter;
import demo.thymeleaf.ThymeleafDialect;

@Configuration
public class ThymeleafConfig extends WebMvcConfigurerAdapter {
		
	
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
	    
	    //	realated locate
	    @Override 
        public void addInterceptors(InterceptorRegistry registry) { 
                registry.addInterceptor(localeChangeInterceptor()); 
        } 

        @Bean 
        public LocaleChangeInterceptor localeChangeInterceptor() { 
                LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor(); 
                localeChangeInterceptor.setParamName("language"); 
                return localeChangeInterceptor; 
        } 

        @Bean 
        public CookieLocaleResolver localeResolver() { 
                CookieLocaleResolver localeResolver = new CookieLocaleResolver(); 
                Locale defaultLocale = new Locale("en"); 
                localeResolver.setDefaultLocale(defaultLocale); 
                return localeResolver; 
        } 
        
        //	filter
       /* @Bean
        public CSRFFilter cSRFFilter(){
			return new CSRFFilter();
			//<bean id="blCsrfFilter" class="org.broadleafcommerce.common.security.handler.CsrfFilter" />
			//<sec:custom-filter ref="blCsrfFilter" before="FORM_LOGIN_FILTER"/>
        }*/
        
        //	realated message
        @Bean 
        public ResourceBundleMessageSource messageSource() { 
                ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource(); 
                messageSource.setBasename("/com/thymeleaf/poc/resources/Messages"); 
                return messageSource; 
        }
	    
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	    }
}

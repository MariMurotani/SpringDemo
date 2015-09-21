package demo.configs;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import demo.thymeleaf.MyMessageResolver;
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
	    @DependsOn("messageSource")
	    public SpringTemplateEngine templateEngine() {
	        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	        templateEngine.setTemplateResolver(templateResolver());
	        templateEngine.addDialect(new ThymeleafDialect());
	        templateEngine.setMessageResolver((IMessageResolver) new MyMessageResolver());
	        
	        return templateEngine;
	    }
	    
	    @Bean
	    public ThymeleafViewResolver viewResolver() {
	        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	        viewResolver.setTemplateEngine(templateEngine());
	        
	        return viewResolver;
	    }
	    
	    /**
         * locale settings
         * */
        @Value("${lang.default.lang}")
		private String defLang;
        @Value("${lang.default.locale}")
		private String defLocale;

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
                Locale defaultLocale = new Locale(defLang + "_" + defLocale); 
                localeResolver.setDefaultLocale(defaultLocale); 
                return localeResolver; 
        } 
        
        @Bean 
        public ReloadableResourceBundleMessageSource messageSource() { 
        	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource(); 
            messageSource.setBasename("classpath:messages/messages"); 
            return messageSource; 
        }
        
        /**
         * resource settings
         * */
        @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	    }
	    
}
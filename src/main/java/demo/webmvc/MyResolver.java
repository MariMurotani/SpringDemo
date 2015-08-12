package demo.webmvc;

import org.thymeleaf.exceptions.ConfigurationException;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.resourceresolver.ServletContextResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

public class MyResolver extends TemplateResolver{

	public MyResolver() {
		super();
		// TODO Auto-generated constructor stub
        super.setResourceResolver(new ServletContextResourceResolver());

	}
	
	@Override
	public void setResourceResolver(final IResourceResolver resourceResolver) {
	    throw new ConfigurationException(
	            "Cannot set a resource resolver on " + this.getClass().getName() + ". If " +
	            "you want to set your own resource resolver, use " + TemplateResolver.class.getName() + 
	            "instead");
	}

}    

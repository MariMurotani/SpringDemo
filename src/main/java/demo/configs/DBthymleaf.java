package demo.configs;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;

public class DBthymleaf extends SpringResourceTemplateResolver{

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		super.setApplicationContext(applicationContext);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		super.afterPropertiesSet();
	}

	@Override
	public void setResourceResolver(IResourceResolver resourceResolver) {
		// TODO Auto-generated method stub
		super.setResourceResolver(resourceResolver);
	}

}

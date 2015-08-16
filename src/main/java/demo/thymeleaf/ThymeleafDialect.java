package demo.thymeleaf;

import java.util.LinkedHashSet;
import java.util.Set;

import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

public class ThymeleafDialect extends StandardDialect {

	
	public ThymeleafDialect() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getPrefix() {
		// TODO Auto-generated method stub
		return "hello";
	}
	
	//
	// The processors.
	//
	@Override
    public Set<IProcessor> getProcessors() {
        final Set<IProcessor> processors = new LinkedHashSet<IProcessor>(35, 1.0f);
        processors.add(new FormProcessor());
        
        return new LinkedHashSet<IProcessor>(processors);
        
    }
	
}

package demo.thymeleaf;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.AbstractXHTMLEnabledDialect;
import org.thymeleaf.processor.IProcessor;

/*
 * http://www.thymeleaf.org/doc/articles/sayhelloextendingthymeleaf5minutes.html
 */
public class ThymeleafDialect extends AbstractDialect {
	public static final String PREFIX = "";

	
	public ThymeleafDialect() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getPrefix() {
		// TODO Auto-generated method stub
		return PREFIX;
	}
	

	// The processors.
	//
	@Override
    public Set<IProcessor> getProcessors() {
		final Set<IProcessor> processors = new LinkedHashSet<IProcessor>();
        processors.add(new demo.thymeleaf.FormProcessor());
        
        return new LinkedHashSet<IProcessor>(processors);
        
    }
	
}

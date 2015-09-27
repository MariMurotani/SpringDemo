package demo.thymeleaf;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;
import org.thymeleaf.Arguments;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.dom.Element;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.processor.IProcessorMatcher;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.attr.AbstractAttrProcessor;
import org.thymeleaf.processor.element.AbstractElementProcessor;
import org.thymeleaf.spring4.naming.SpringContextVariableNames;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import demo.configs.Const;
import demo.libs.Encoder;

public class FormProcessor extends AbstractElementProcessor {
	static final String ELEMENT_NAME_FORM = "form";
	
	public FormProcessor() {
		super(ELEMENT_NAME_FORM);
	}

	@Override
	protected ProcessorResult processElement(Arguments arguments,Element element) {
		try{
			addCSRFHiddenFields(arguments, element);
			System.out.println(element.getNormalizedName());
			return ProcessorResult.OK;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public IProcessorMatcher<? extends Element> getMatcher() {
		// TODO Auto-generated method stub
		return super.getMatcher();
	}
	
	private void addCSRFHiddenFields(Arguments arguments, Element element) {
		System.out.println("addExtraHiddenFields");
		//element.getAttributeFromNormalizedName("form");
		element.normalizeElementName("form");
		
		
		if (!"GET".equalsIgnoreCase(element.getAttributeValueFromNormalizedName("method"))) {
            try {
    
            	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                HttpSession httpSession = request.getSession();
                
                String csrfTokenName = Const.CRSF_PREFIX + Encoder.getRandomMd5();
            	String csrfTokenValue = Encoder.getRandomMd5();
            	httpSession.setAttribute(csrfTokenName, csrfTokenValue);
                
            	Element csrfNode = new Element("input");
                csrfNode.setAttribute("type", "hidden");
                csrfNode.setAttribute("name", csrfTokenName);
                csrfNode.setAttribute("value", csrfTokenValue);
                element.addChild(csrfNode);
            	
            } catch (Exception e) {
                throw new RuntimeException("Could not get a CSRF token for this session", e);
            }
        }
		
	}


	private void addHidden(Element form, String name, String value) {
		Element hiddenElement = new Element("input");
		hiddenElement.setAttribute("type", "hidden");
		hiddenElement.setAttribute("name", name);
		hiddenElement.setAttribute("value", value);

		form.addChild(hiddenElement);
	}

	@Override
	public int getPrecedence() {
		return 1300;
	}



}
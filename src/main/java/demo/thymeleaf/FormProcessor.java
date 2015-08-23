package demo.thymeleaf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.security.MD5Encoder;
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

import demo.libs.Encoder;


public class FormProcessor extends AbstractElementProcessor {
	static final String ELEMENT_NAME_FORM = "form";

	public FormProcessor() {
		super(ELEMENT_NAME_FORM);
	}

	@Override
	protected ProcessorResult processElement(Arguments arguments,Element element) {
		addCSRFHiddenFields(arguments, element);
		System.out.println(element.getNormalizedName());
		return ProcessorResult.OK;
	}
	
	@Override
	public IProcessorMatcher<? extends Element> getMatcher() {
		// TODO Auto-generated method stub
		return super.getMatcher();
	}
	
	/*@Override
	protected ProcessorResult processAttribute(Arguments arguments, Element element, String attributeName) {
		if (element.getNormalizedName() != "form") {
			throw new TemplateProcessingException("Form annontation must be used on a form element");
		}

		element.removeAttribute(attributeName);
		addExtraHiddenFields(arguments, element);

		return ProcessorResult.OK;
	}
	*/
	
	private void addCSRFHiddenFields(Arguments arguments, Element element) {
		System.out.println("addExtraHiddenFields");
		//element.getAttributeFromNormalizedName("form");
		element.normalizeElementName("form");
		
		
		if (!"GET".equalsIgnoreCase(element.getAttributeValueFromNormalizedName("method"))) {
            try {
            	//CryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
            	//String hashedPassword = passwordEncoder.encode(password);  
            	String csrfTokenName = "TOKEN_" + Encoder.getRandomMd5();
            	String csrfTokenValue = Encoder.getRandomMd5();
                Element csrfNode = new Element("input");
                csrfNode.setAttribute("type", "hidden");
                csrfNode.setAttribute("name", csrfTokenName);
                csrfNode.setAttribute("value", csrfTokenValue);
                element.addChild(csrfNode);
            	
            } catch (Exception e) {
                throw new RuntimeException("Could not get a CSRF token for this session", e);
            }
        }
		
		//return element.cloneElementNodeWithNewName(element.getParent(),"form",true);
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
package demo.configs;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

public class MyCustomResourceHttpRequestHandler extends ResourceHttpRequestHandler {

    private String yourCustomPath;

    @Override
    protected Resource getResource(HttpServletRequest request) {
    	String path = (String)  request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

       //put whatever logic you need in here
       return new FileSystemResource(yourCustomPath + path);

    }

    public void setYourCustomPath(String path){
        this.yourCustomPath = path;
    }
}

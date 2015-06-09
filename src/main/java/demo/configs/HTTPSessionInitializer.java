package demo.configs;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

class HTTPSessionInitializer extends AbstractHttpSessionApplicationInitializer {
    public HTTPSessionInitializer() {
        super(HttpSessionConfig.class);
    	System.out.println("session initializer called");
    }
}

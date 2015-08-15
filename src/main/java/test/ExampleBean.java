package test;

import org.springframework.beans.factory.InitializingBean;


public class ExampleBean implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("initialize bean after process");
	}
	
	
   /*public void init() {
      // do some initialization work
   }*/
}

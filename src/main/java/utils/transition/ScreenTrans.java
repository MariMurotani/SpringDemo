package utils.transition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.*;

/*
 * This annotaion is valid in runtime
 * */
//@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ScreenTrans {
	public String referer() default "*";
	public boolean usetoken() default false;
}

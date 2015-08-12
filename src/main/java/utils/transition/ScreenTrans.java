package utils.transition;

import java.lang.annotation.Retention;

/*
 * This annotaion is valid in runtime
 * */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface ScreenTrans {
	String referer() default "*";
	boolean usetoken() default false;
}

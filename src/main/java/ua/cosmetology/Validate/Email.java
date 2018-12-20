package ua.cosmetology.Validate;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {
	
	String message() default "Your email is not correct";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	

}

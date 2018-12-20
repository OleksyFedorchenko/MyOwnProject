package ua.cosmetology.Validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.cosmetology.Repository.LogRepository;
import ua.cosmetology.Repository.MasterRepository;

@Component
public class EmailValidator implements ConstraintValidator<Email, String>{
    
	@Autowired
	private LogRepository logRepository;
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String emailPattern = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
	
	public EmailValidator() {
		
		pattern = Pattern.compile(emailPattern);
		
	}
	
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		
		
		return value != null && value.matches(emailPattern);
	}
	
	
	
	

}

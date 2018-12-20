package ua.cosmetology.Validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.cosmetology.Repository.ClientRepository;
import ua.cosmetology.Repository.MasterRepository;

@Component
public class PhoneNumberValidation implements ConstraintValidator<PhoneNumber, String> {
	
	@Autowired
	private MasterRepository masterRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	private Pattern pattern;
	
	private Matcher matcher;
	
	private static final String phoneNumber = "^\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";
	
	 public PhoneNumberValidation() {

		pattern = Pattern.compile(phoneNumber);
		
	}
	
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		
		
		return value != null && value.matches(phoneNumber);
	}
	
	
	
	
	

}

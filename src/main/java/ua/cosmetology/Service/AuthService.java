package ua.cosmetology.Service;

import ua.cosmetology.DTO.SignInRequest;
import ua.cosmetology.DTO.SignUpRequest;

public interface AuthService {
	
	void signup(SignUpRequest signUpRequest);
	
	String signin(SignInRequest signinRequest);
	
	

}

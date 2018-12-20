package ua.cosmetology.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cosmetology.DTO.SignInRequest;
import ua.cosmetology.DTO.SignInResponse;
import ua.cosmetology.DTO.SignUpRequest;
import ua.cosmetology.Service.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("signup")
	public ResponseEntity<?> singup(@RequestBody SignUpRequest request){
		authService.signup(request);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	 @PostMapping("signin")
	public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest){
		 
		 String token = authService.signin(signInRequest);
		 
		 return new ResponseEntity<>(new SignInResponse(token), HttpStatus.OK);
		 
	 }
}

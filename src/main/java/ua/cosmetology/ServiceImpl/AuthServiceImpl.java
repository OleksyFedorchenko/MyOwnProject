package ua.cosmetology.ServiceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.cosmetology.DTO.SignInRequest;
import ua.cosmetology.DTO.SignUpRequest;
import ua.cosmetology.Entity.LogEntity;
import ua.cosmetology.Entity.RoleEntity;
import ua.cosmetology.Exception.AlreadyExistsException;
import ua.cosmetology.Exception.NotFoundException;
import ua.cosmetology.Repository.LogRepository;
import ua.cosmetology.Repository.RoleRepository;
import ua.cosmetology.Service.AuthService;
import ua.cosmetology.config.jwt.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private LogRepository logRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	
	
	@Override
	public void signup(SignUpRequest signUpRequest) {

		if(logRepository.existsByEmail(signUpRequest.getEmail())) {
			
			throw new AlreadyExistsException("User with email " + signUpRequest.getEmail() + " already exist");
		}
		
		LogEntity logEntity = new LogEntity();
		logEntity.setEmail(signUpRequest.getEmail());
		logEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		logEntity.setClientEntity(signUpRequest.getClientEntity());
		RoleEntity role = roleRepository.findByName("USER").orElseThrow(
				() -> new NotFoundException("Role not found")
				);
		
		Set<RoleEntity> roles = new HashSet<>();
		roles.add(role);
		logEntity.setRoles(roles);
		logRepository.save(logEntity);
		
	}


	@Override
	public String signin(SignInRequest signinRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						signinRequest.getEmail(), 
						signinRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);
		
		return token;
	}

}

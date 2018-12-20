package ua.cosmetology.ServiceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.cosmetology.Entity.LogEntity;
import ua.cosmetology.Repository.LogRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
	private LogRepository logRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		LogEntity entity = logRepository.findByEmail(email).orElseThrow(
				() -> new  UsernameNotFoundException("User with email [" + email + "] not found")
				);
		
		return User.
				builder()
				.username(entity.getEmail())
				.password(entity.getPassword())
				.authorities(getAuthority(entity))
				.build();
	}
	
	
	private Set<SimpleGrantedAuthority> getAuthority(LogEntity entity){
		
		Set<SimpleGrantedAuthority> authorithes = new HashSet<>();
		entity.getRoles().forEach(role -> {
			authorithes.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorithes;
		
		
		
	}

	
}

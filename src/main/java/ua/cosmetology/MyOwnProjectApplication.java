package ua.cosmetology;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import ua.cosmetology.Entity.LogEntity;
import ua.cosmetology.Entity.RoleEntity;
import ua.cosmetology.Exception.NotFoundException;
import ua.cosmetology.Repository.LogRepository;
import ua.cosmetology.Repository.RoleRepository;

@SpringBootApplication
public class MyOwnProjectApplication implements CommandLineRunner{
    @Autowired
	private RoleRepository roleRepository;
    
    @Autowired
    private LogRepository logRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(MyOwnProjectApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		if(roleRepository.count() == 0) {
			RoleEntity entity = new RoleEntity();
			entity.setName("ADMIN");
			
			roleRepository.save(entity);
			
			entity = new RoleEntity();
			entity.setName("USER");
			
			roleRepository.save(entity);
			
		}
		
		if(logRepository.count() == 0) {
			
			LogEntity  logEntity = new LogEntity();
			logEntity.setEmail("Adminchik@fmekmefk.com");
			logEntity.setPassword(passwordEncoder.encode("12345"));
			
			RoleEntity role = roleRepository.findByName("USER").orElseThrow(
					() -> new NotFoundException("Role not found")
					);
			
			Set<RoleEntity> roles = new HashSet<>();
			roles.add(role);
			logEntity.setRoles(roles);
			
			logRepository.save(logEntity);
		
			
		}
		
	}
}

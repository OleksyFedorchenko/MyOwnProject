package ua.cosmetology.DTO;

import lombok.Data;
import ua.cosmetology.Entity.ClientEntity;

@Data
public class SignUpRequest {
	
	private String email;
	private String password;
	private ClientEntity clientEntity;
	

}

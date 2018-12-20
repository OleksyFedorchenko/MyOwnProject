package ua.cosmetology.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.cosmetology.Entity.ClientEntity;
import ua.cosmetology.Validate.Email;


@Data
public class LogDTO {
	
	private Long id;
	
	private String email;
	
	private String password;
	
	private ClientEntity clientEntity;
	
}

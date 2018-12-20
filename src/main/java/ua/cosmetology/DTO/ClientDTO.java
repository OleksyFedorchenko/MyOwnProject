package ua.cosmetology.DTO;

import java.time.LocalDate;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import ua.cosmetology.Validate.Email;
import ua.cosmetology.Validate.PhoneNumber;

@Data
public class ClientDTO {
	
	private Long id;
	
	@NotNull(message="Fist name cannot be null")
	@Size(min = 0, max = 14)
    private String firstName;
	
	@NotNull(message="Last name cannot be null")
	@Size(min = 0, max = 14)
	private String lastName;
	
	@Size(min = 0, max = 12)
	private String phoneNumber;
	
	private LocalDate birthday;
	

}

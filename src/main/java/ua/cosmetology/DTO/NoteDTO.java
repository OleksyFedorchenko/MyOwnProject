package ua.cosmetology.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.Data;
import ua.cosmetology.Entity.ClientEntity;
import ua.cosmetology.Entity.MasterEntity;
import ua.cosmetology.Entity.ServiceEntity;

@Data
public class NoteDTO {
	
	private Long id;
	
	private ClientEntity clientEntity;
	
	@NotNull(message = "Enter your master")
    private MasterEntity masterEntity;
	
	@NotNull(message = "Enter services what do you want")
	private ServiceEntity serviceEntity;
	
	@NotNull(message = "Enter Date")
	private LocalDate date;
	
	@NotNull(message = "Enter Time")
	private LocalTime time;
	

}

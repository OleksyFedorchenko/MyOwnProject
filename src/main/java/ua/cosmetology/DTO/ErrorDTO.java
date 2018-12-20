package ua.cosmetology.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorDTO {
	
	private String message;
	
	private LocalDateTime time;
	
	public ErrorDTO(String message) {
		
		this.message = message;
		this.time = LocalDateTime.now();
		
	}
	
	

}

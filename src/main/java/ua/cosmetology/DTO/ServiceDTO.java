package ua.cosmetology.DTO;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ServiceDTO {
	
	private Long id;
	
	@NotNull
    private String nameProcedure;
	
	@DecimalMax(value = "999.99")
	@DecimalMin(value = "0.0")
	private BigDecimal price;

}

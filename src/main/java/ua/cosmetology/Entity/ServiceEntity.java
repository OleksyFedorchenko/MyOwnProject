package ua.cosmetology.Entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Service")
public class ServiceEntity extends BaseEntity {
	
	@Column(nullable = false, length = 80)
	private String nameProcedure;
	
	@Column(nullable = false, columnDefinition = "DECIMAL (6, 2)")
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "note_id")
	private NoteEntity note;
	
	
}

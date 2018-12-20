package ua.cosmetology.Entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Note")
public class NoteEntity extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private ClientEntity clientEntity;
	
	@ManyToOne
	@JoinColumn(name = "master_id")
	private MasterEntity masterEntity;
	
	@OneToMany(mappedBy = "note")
	private List<ServiceEntity> serviceEntity;
	
	private LocalDate date;
	
	private LocalTime time;
	

}

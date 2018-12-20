package ua.cosmetology.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Client")
public class ClientEntity extends BaseEntity{
	
	@Column(length = 30, nullable = false)
	private String firstName;
	@Column(length = 30, nullable = false)
	private String lastName;
	@Column(length = 30, unique = true, nullable = false)
	private String phoneNumber;
	@Column(length = 20, nullable = false, columnDefinition = "DATE")
	@JsonFormat(pattern="yyyy-mm-dd")
	private LocalDate birthday;
	@OneToOne(mappedBy = "clientEntity")
	private LogEntity logEntity;
	
	@OneToMany(mappedBy = "clientEntity")
	private List<NoteEntity> notes;

}

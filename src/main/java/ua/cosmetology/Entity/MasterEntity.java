package ua.cosmetology.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Master")
public class MasterEntity extends BaseEntity{
	@Column(nullable = false, length = 20)
    private String firstName;
	
	@Column(nullable = false, length = 20)
	private String lastName;
	
	@Column(nullable = false, length = 20)
	private String phoneNumber;
	
	@Column(nullable = false, length = 20, columnDefinition = "DATE")
	@JsonFormat(pattern = "yyyy-mm-dd")
	private LocalDate birthday;
	
	@Column(nullable = true)
	private String image;
	
	
	@OneToMany(mappedBy = "masterEntity")
	private List<NoteEntity> notes;
	
	
	

}

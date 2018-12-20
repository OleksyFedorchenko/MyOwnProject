package ua.cosmetology.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {
	
	@Column(nullable = false)
	private String name;
	
	

}

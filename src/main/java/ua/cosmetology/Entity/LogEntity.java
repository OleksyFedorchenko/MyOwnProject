package ua.cosmetology.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.cosmetology.Validate.Email;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Log")
public class LogEntity extends BaseEntity {
	
	@Column(nullable = false, unique = true, length = 130)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@OneToOne
	@JoinColumn(name = "client_id")
	private ClientEntity clientEntity;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "log_roles",
			   joinColumns = @JoinColumn(name = "log_id"),
			   inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;
	
	

}

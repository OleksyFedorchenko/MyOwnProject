package ua.cosmetology.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.cosmetology.Entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
	
	ClientEntity findByFirstName(String firstName);
	
	ClientEntity findByPhoneNumber(String phoneNumber);

}

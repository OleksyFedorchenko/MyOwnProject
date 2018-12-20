package ua.cosmetology.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.cosmetology.Entity.MasterEntity;

@Repository
public interface MasterRepository extends JpaRepository<MasterEntity, Long>{
	
	MasterEntity findByPhoneNumber(String phoneNumber);
	
	

}

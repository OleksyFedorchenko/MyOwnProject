package ua.cosmetology.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.cosmetology.Entity.LogEntity;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long>{
	
	Optional<LogEntity> findByEmail(String email);
	
	Boolean existsByEmail(String email);

}

package ua.cosmetology.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.cosmetology.Entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	
	Optional<RoleEntity> findByName(String name);

}

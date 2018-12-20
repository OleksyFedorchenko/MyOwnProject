package ua.cosmetology.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import ua.cosmetology.DTO.MasterDTO;
import ua.cosmetology.Entity.MasterEntity;

public interface MasterService {

	void createMaster(MasterDTO masterDTO);
	
	List<MasterDTO> findAll();
	
	MasterDTO findById(Long id);
	
	void deleteById(Long id, MasterDTO masterDTO);
	
	void updateMasterById(Long id, MasterDTO masterDTO);
	
	List<MasterDTO> findMasterByPage(Pageable pageable);
	
	void addImageToMasters(String image, Long id);
	
	Long findByPhoneNumber(String phoneNumber);
	
}

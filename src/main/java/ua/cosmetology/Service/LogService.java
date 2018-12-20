package ua.cosmetology.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import ua.cosmetology.DTO.LogDTO;

public interface LogService {
	
	void createLog(LogDTO logDTO);
	
	List<LogDTO> findAll();
	
	LogDTO findById(Long Id);
	
	void updateLogDTO(Long id, LogDTO logDTO);
	
	List<LogDTO> findAllByPage(Pageable pageable);
	

}

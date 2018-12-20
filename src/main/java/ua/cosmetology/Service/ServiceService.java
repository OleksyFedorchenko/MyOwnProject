package ua.cosmetology.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import ua.cosmetology.DTO.ServiceDTO;

public interface ServiceService {
	
	 void createService(ServiceDTO serviceDTO);
	
	 List<ServiceDTO> findAll();
	 
	 ServiceDTO findById(Long id);
	 
	 void deleteById(Long id, ServiceDTO serviceDTO);
	 
	 void updateServiceById(Long id, ServiceDTO serviceDTO);
	 
	 List<ServiceDTO> findServiceByPage(Pageable pageable);

}

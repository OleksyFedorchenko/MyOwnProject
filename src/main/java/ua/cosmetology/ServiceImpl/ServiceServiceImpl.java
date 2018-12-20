package ua.cosmetology.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.cosmetology.DTO.ServiceDTO;
import ua.cosmetology.Entity.ServiceEntity;
import ua.cosmetology.Repository.ServiceRepository;
import ua.cosmetology.Service.ServiceService;
import ua.cosmetology.Utils.ObjectMapped;

@Service
public class ServiceServiceImpl implements ServiceService {
	@Autowired
    private ServiceRepository serviceRepository;
	@Autowired
	private ObjectMapped objectmapped;
	
	@Override
	public void createService(ServiceDTO serviceDTO) {
          ServiceEntity serviceEntity = objectmapped.map(serviceDTO, ServiceEntity.class);
            serviceRepository.save(serviceEntity);
          }

	@Override
	public List<ServiceDTO> findAll() {
		List<ServiceEntity> serviceEntity = serviceRepository.findAll();
		List<ServiceDTO> serviceDTO = objectmapped.mapAll(serviceEntity, ServiceDTO.class);
		return serviceDTO;
	}

	@Override
	public ServiceDTO findById(Long id) {
		ServiceEntity serviceEntity = serviceRepository.findById(id).get();
		ServiceDTO serviceDTO = objectmapped.map(serviceEntity, ServiceDTO.class);
		return serviceDTO;
	}

	@Override
	public void deleteById(Long id, ServiceDTO serviceDTO) {
		ServiceEntity serviceEntity = objectmapped.map(serviceDTO, ServiceEntity.class);
		serviceRepository.deleteById(id);
	}

	@Override
	public void updateServiceById(Long id, ServiceDTO serviceDTO) {
              ServiceEntity serviceEntity = objectmapped.map(serviceDTO, ServiceEntity.class);
              serviceEntity.setId(id);
              serviceRepository.save(serviceEntity);
	}

	@Override
	public List<ServiceDTO> findServiceByPage(Pageable pageable) {
		Page<ServiceEntity> serviceEntity = serviceRepository.findAll(pageable);
		List<ServiceDTO> serviceDTO = objectmapped.mapAll(serviceEntity.getContent(), ServiceDTO.class);
		
		return serviceDTO;
	}

}

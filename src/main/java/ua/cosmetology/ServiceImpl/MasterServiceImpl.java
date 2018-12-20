package ua.cosmetology.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.cosmetology.DTO.MasterDTO;
import ua.cosmetology.Entity.MasterEntity;
import ua.cosmetology.Exception.NotFoundException;
import ua.cosmetology.Repository.MasterRepository;
import ua.cosmetology.Service.MasterService;
import ua.cosmetology.Utils.ObjectMapped;

@Service
public class MasterServiceImpl implements MasterService{
	@Autowired
	private ObjectMapped objectMapped;
	@Autowired
	private MasterRepository masterRepository;
	
	@Override
	public void createMaster(MasterDTO masterDTO) {
    		MasterEntity masterEntity = objectMapped.map(masterDTO, MasterEntity.class);
    		masterRepository.save(masterEntity);
	}
	@Override
	public List<MasterDTO> findAll() {
		List<MasterEntity> masterEntity = masterRepository.findAll();
		List<MasterDTO> masterDTO = objectMapped.mapAll(masterEntity, MasterDTO.class);
		return masterDTO;
	}
	@Override
	public MasterDTO findById(Long id) {
		MasterEntity masterEntity = masterRepository.findById(id).get();
		MasterDTO masterDTO = objectMapped.map(masterEntity, MasterDTO.class);
		return masterDTO;
	}
	@Override
	public void deleteById(Long id, MasterDTO masterDTO) {
		MasterEntity masterEntity = objectMapped.map(masterDTO, MasterEntity.class);
		masterEntity.setId(id);
		masterRepository.deleteById(id);
	}
	@Override
	public void updateMasterById(Long id, MasterDTO masterDTO) {
            MasterEntity masterEntity = objectMapped.map(masterDTO, MasterEntity.class);
            masterEntity.setId(id);
            masterRepository.save(masterEntity);
	}
	@Override
	public List<MasterDTO> findMasterByPage(Pageable pageable) {
		Page<MasterEntity> masterEntity = masterRepository.findAll(pageable);
		List<MasterDTO> masterDTO = objectMapped.mapAll(masterEntity.getContent(), MasterDTO.class);
		return masterDTO;
	}
	@Override
	public void addImageToMasters(String image, Long id) {

		MasterEntity masterEntity = masterRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Product with id [" + id + "] not found")
                );
        masterEntity.setImage(image);
        masterRepository.save(masterEntity);
		
		
	}
	@Override
	public Long findByPhoneNumber(String phoneNumber) {
		
		MasterEntity masterEntity = masterRepository.findByPhoneNumber(phoneNumber);
		
		
		return masterEntity.getId();
	}
	
	

}

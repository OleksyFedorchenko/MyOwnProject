package ua.cosmetology.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.cosmetology.DTO.LogDTO;
import ua.cosmetology.Entity.LogEntity;
import ua.cosmetology.Exception.NotFoundException;
import ua.cosmetology.Repository.LogRepository;
import ua.cosmetology.Service.LogService;
import ua.cosmetology.Utils.ObjectMapped;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private ObjectMapped objectMapped;

	@Override
	public void createLog(LogDTO logDTO) {

		LogEntity logEntity = objectMapped.map(logDTO, LogEntity.class);
		logRepository.save(logEntity);

	}

	@Override
	public List<LogDTO> findAll() {

		List<LogEntity> logEntity = logRepository.findAll();
		List<LogDTO> logDTO = objectMapped.mapAll(logEntity, LogDTO.class);
		return logDTO;
	}

	@Override
	public LogDTO findById(Long id) {

		LogEntity logEntity = logRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Log with id " + id + " not found"));
		LogDTO logDTO = objectMapped.map(logEntity, LogDTO.class);

		return logDTO;
	}

	@Override
	public void updateLogDTO(Long id, LogDTO logDTO) {

		LogEntity logEntity = objectMapped.map(logDTO, LogEntity.class);
		logEntity.setId(id);
		logRepository.save(logEntity);

	}

	@Override
	public List<LogDTO> findAllByPage(Pageable pageable) {
		Page<LogEntity> logEntity = logRepository.findAll(pageable);
		List<LogDTO> logDTO = objectMapped.mapAll(logEntity.getContent(), LogDTO.class);
		return logDTO;
	}

}

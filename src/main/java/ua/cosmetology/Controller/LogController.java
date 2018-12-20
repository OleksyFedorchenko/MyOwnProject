package ua.cosmetology.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cosmetology.DTO.ErrorDTO;
import ua.cosmetology.DTO.LogDTO;
import ua.cosmetology.Service.LogService;

@RestController
@RequestMapping("logs")
public class LogController {

	@Autowired
	private LogService logService;

	@PostMapping
	public ResponseEntity<?> createLog(@Valid @RequestBody LogDTO logDTO, BindingResult br) {

		if (br.hasErrors()) {

			String errMsg = br.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.findFirst().get().toString();
			ErrorDTO error = new ErrorDTO(errMsg);
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

		logService.createLog(logDTO);

		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<List<LogDTO>> finadAll(@PageableDefault Pageable pageable) {

		List<LogDTO> logDTO = logService.findAllByPage(pageable);

		return new ResponseEntity<List<LogDTO>>(logDTO, HttpStatus.OK);

	}

	@GetMapping("{logId}")
	public ResponseEntity<LogDTO> findById(@PathVariable("logId") Long id) {

		LogDTO logDTO = logService.findById(id);

		return new ResponseEntity<LogDTO>(logDTO, HttpStatus.OK);

	}

	@PostMapping("{logId}")
	public ResponseEntity<?> updateById(@Valid @PathVariable("logId") Long id, LogDTO logDTO, BindingResult br) {

		if (br.hasErrors()) {

			String errMsg = br.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.findFirst().toString();
			ErrorDTO error = new ErrorDTO(errMsg);
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

		logService.updateLogDTO(id, logDTO);

		return new ResponseEntity<Void>(HttpStatus.OK);

	}

}

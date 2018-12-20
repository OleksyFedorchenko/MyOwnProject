package ua.cosmetology.Controller;

import java.util.List;

import javax.validation.Valid;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cosmetology.DTO.ErrorDTO;
import ua.cosmetology.DTO.ServiceDTO;
import ua.cosmetology.Service.ServiceService;

@RestController
@RequestMapping("services")
public class ServiceController {

	@Autowired
	private ServiceService serviceService;

	@PostMapping
	public ResponseEntity<?> createService(@Valid @RequestBody ServiceDTO serviceDTO, BindingResult br) {

		if (br.hasErrors()) {

			String errMsg = br.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.findFirst().toString();
			ErrorDTO error = new ErrorDTO(errMsg);
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

		serviceService.createService(serviceDTO);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ServiceDTO>> findAll(@PageableDefault Pageable pageable) {

		List<ServiceDTO> serviceDTO = serviceService.findServiceByPage(pageable);

		return new ResponseEntity<List<ServiceDTO>>(serviceDTO, HttpStatus.OK);
	}

	@GetMapping("{serviceId}")
	public ResponseEntity<ServiceDTO> findServiceById(@PathVariable("serviceId") Long id) {

		ServiceDTO serviceDTO = serviceService.findById(id);

		return new ResponseEntity<ServiceDTO>(serviceDTO, HttpStatus.OK);
	}

	@PostMapping("{serviceId}")
	public ResponseEntity<?> updateService(@Valid @PathVariable("serviceId") Long id, ServiceDTO serviceDTO,
			BindingResult br) {

		if (br.hasErrors()) {

			String errMsg = br.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.findFirst().toString();
			ErrorDTO error = new ErrorDTO(errMsg);
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

		serviceService.updateServiceById(id, serviceDTO);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("{serviceId}")
	public ResponseEntity<?> deleteService(@PathVariable("serviceId") Long id, ServiceDTO serviceDTO) {

		serviceService.deleteById(id, serviceDTO);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

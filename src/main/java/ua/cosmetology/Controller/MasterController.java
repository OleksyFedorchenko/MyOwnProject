package ua.cosmetology.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ua.cosmetology.DTO.ErrorDTO;
import ua.cosmetology.DTO.MasterDTO;
import ua.cosmetology.Service.FileStorageService;
import ua.cosmetology.Service.MasterService;

@RestController
@RequestMapping("masters")
public class MasterController {

	@Autowired
	private MasterService masterService;
	
	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping
	public ResponseEntity<?> createMaster(@Valid @RequestBody MasterDTO masterDTO, BindingResult br) {

		if (br.hasErrors()) {

			String errMsg = br.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.findFirst().get().toString();
			ErrorDTO error = new ErrorDTO(errMsg);
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

		masterService.createMaster(masterDTO);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<MasterDTO>> findAll(@PageableDefault Pageable pageable) {

		List<MasterDTO> masters = masterService.findMasterByPage(pageable);

		return new ResponseEntity<List<MasterDTO>>(masters, HttpStatus.OK);
	}

	@GetMapping("{masterId}")
	public ResponseEntity<MasterDTO> findById(@PathVariable("masterId") Long id) {

		MasterDTO masterDTO = masterService.findById(id);

		return new ResponseEntity<MasterDTO>(masterDTO, HttpStatus.OK);
	}

	@PostMapping("{masterId}")
	public ResponseEntity<?> updateMaster(@Valid @PathVariable("masterId") Long id, MasterDTO masterDTO,
			BindingResult br) {

		if (br.hasErrors()) {

			String errMsg = br.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.findFirst().toString();
			ErrorDTO error = new ErrorDTO(errMsg);
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

		masterService.updateMasterById(id, masterDTO);

		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@DeleteMapping("{masterId}")
	public ResponseEntity<?> deleteMaster(@PathVariable("masterId") Long id, MasterDTO masterDTO) {

		masterService.deleteById(id, masterDTO);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("{masterId}/image")
	public ResponseEntity<?> uploadImage(@PathVariable("masterId") Long id,
										 @RequestParam("file") MultipartFile file ){
		
		fileStorageService.saveFile(file);
		masterService.addImageToMasters(file.getOriginalFilename(), id);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("image")
	public ResponseEntity<?> getFile(@RequestParam("fileName") String fileName,
			                         HttpServletRequest request){
		
		Resource resource = fileStorageService.loadFile(fileName);
		
		String contentType = null;
		try {
			
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return  ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline: fileName=\"" + resource.getFilename() + "\"").body(resource);
	}
	
	@GetMapping("{phone}/phone")
	public ResponseEntity<Long> findByPhoneNumber(@PathVariable("phone") String phoneNumber){
		
		return ResponseEntity.ok(masterService.findByPhoneNumber(phoneNumber));
		
		
		
	}

}

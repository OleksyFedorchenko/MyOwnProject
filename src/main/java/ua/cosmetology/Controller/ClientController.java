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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.cosmetology.DTO.ClientDTO;
import ua.cosmetology.DTO.ErrorDTO;
import ua.cosmetology.Service.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping
	public ResponseEntity<?> createClient(@Valid @RequestBody ClientDTO clientDTO, BindingResult br){
		
		if(br.hasErrors()) {
			
			String errMsg = br.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().get().toString();
			ErrorDTO error = new ErrorDTO(errMsg);
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		
		clientService.createClient(clientDTO);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAllClient(@PageableDefault Pageable pageable){
		
		List<ClientDTO> clients = clientService.findClientByPage(pageable);
		
		return new ResponseEntity<List<ClientDTO>>(clients, HttpStatus.OK);
	}
	
	@GetMapping("{clientId}")
	public ResponseEntity<ClientDTO> findClientById(@PathVariable("clientId")Long id){
		
		ClientDTO client = clientService.findById(id);
		
		return new ResponseEntity<ClientDTO>(client, HttpStatus.OK);
	}
	
	@PostMapping("{clientId}")
	public ResponseEntity<?> updateClient(@Valid @PathVariable("clientId")Long id, ClientDTO clientDTO, BindingResult br){
if(br.hasErrors()) {
			
			String errMsg = br.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().toString();
			ErrorDTO error = new ErrorDTO(errMsg);
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		
		clientService.updateClientbyId(id, clientDTO);
		
		
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	@DeleteMapping("{clientId}")
	public ResponseEntity<?> deleteClient(@PathVariable("clientId")Long id, ClientDTO clientDTO){
		
		clientService.deleteClientById(id, clientDTO);
		
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	@GetMapping("{phone}/phone")
	public ResponseEntity<Long> findByPhoneNumber(@PathVariable("phone") String phoneNumber){
		
		
		return ResponseEntity.ok(clientService.findByPhoneNumber(phoneNumber));
	}
	

}

package ua.cosmetology.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import ua.cosmetology.DTO.ClientDTO;

public interface ClientService {
	
	void createClient(ClientDTO clientDTO);
	
	List<ClientDTO> findAll();
	
	ClientDTO findById(Long id);
	
	void deleteClientById(Long id, ClientDTO clientDTO);
	
	void updateClientbyId(Long id, ClientDTO clientDTO);
	
	List<ClientDTO> findClientByPage(Pageable pageable);
	
	Long findByPhoneNumber(String phoneNumber);
}

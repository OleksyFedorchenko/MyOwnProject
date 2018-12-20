package ua.cosmetology.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.cosmetology.DTO.ClientDTO;
import ua.cosmetology.Entity.ClientEntity;
import ua.cosmetology.Exception.NotFoundException;
import ua.cosmetology.Repository.ClientRepository;
import ua.cosmetology.Service.ClientService;
import ua.cosmetology.Utils.ObjectMapped;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ObjectMapped objectmapped;
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public void createClient(ClientDTO clientDTO) {
		ClientEntity clientEntity = objectmapped.map(clientDTO, ClientEntity.class);

		clientRepository.save(clientEntity);
	}

	@Override
	public List<ClientDTO> findAll() {
		List<ClientEntity> clientEntity = clientRepository.findAll();
		List<ClientDTO> clientDTO = objectmapped.mapAll(clientEntity, ClientDTO.class);
		return clientDTO;
	}

	@Override
	public ClientDTO findById(Long id) {
		ClientEntity clientEntity = clientRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Client with id " + id + " not found"));
		ClientDTO clientDTO = objectmapped.map(clientEntity, ClientDTO.class);
		return clientDTO;
	}

	@Override
	public void deleteClientById(Long id, ClientDTO clientDTO) {
		ClientEntity clientEntity = objectmapped.map(clientDTO, ClientEntity.class);
		clientEntity.setId(id);
		clientRepository.deleteById(id);

	}

	@Override
	public void updateClientbyId(Long id, ClientDTO clientDTO) {
		ClientEntity clientEntity = objectmapped.map(clientDTO, ClientEntity.class);
		clientEntity.setId(id);
		clientRepository.save(clientEntity);
	}


	@Override
	public List<ClientDTO> findClientByPage(Pageable pageable) {

		Page<ClientEntity> clientEntity = clientRepository.findAll(pageable);
		List<ClientDTO> clientDTO = objectmapped.mapAll(clientEntity.getContent(), ClientDTO.class);
		return clientDTO;
	}

	@Override
	public Long findByPhoneNumber(String phoneNumber) {
        
	 ClientEntity clientEntity = clientRepository.findByPhoneNumber(phoneNumber);
		return clientEntity.getId();
	}
}

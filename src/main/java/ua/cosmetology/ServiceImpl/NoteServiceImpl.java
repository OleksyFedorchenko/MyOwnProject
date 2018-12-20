package ua.cosmetology.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.cosmetology.DTO.NoteDTO;
import ua.cosmetology.Entity.NoteEntity;
import ua.cosmetology.Repository.NoteRepository;
import ua.cosmetology.Service.NoteService;
import ua.cosmetology.Utils.ObjectMapped;

@Service
public class NoteServiceImpl implements NoteService{
	
	@Autowired
	private ObjectMapped objectMapped;
	@Autowired
    private NoteRepository noteRepository;
	
	@Override
	public void createNote(NoteDTO noteDTO) {
      NoteEntity noteEntity = objectMapped.map(noteDTO, NoteEntity.class);
             noteRepository.save(noteEntity);
	}

	@Override
	public List<NoteDTO> findAll() {
		List<NoteEntity> noteEntity = noteRepository.findAll();
		List<NoteDTO> noteDTO = objectMapped.mapAll(noteEntity, NoteDTO.class);
		return noteDTO;
	}

	@Override
	public NoteDTO findById(Long id) {
		NoteEntity noteEntity = noteRepository.findById(id).get();
		NoteDTO noteDTO = objectMapped.map(noteEntity, NoteDTO.class);
		return noteDTO;
	}

	@Override
	public void deleteById(Long id, NoteDTO noteDTO) {
		NoteEntity noteEntity = objectMapped.map(noteDTO, NoteEntity.class);
		noteRepository.deleteById(id);
	}

	@Override
	public void updateNoteById(Long id, NoteDTO noteDTO) {
        NoteEntity noteEntity = objectMapped.map(noteDTO, NoteEntity.class);
        noteEntity.setId(id);
        noteRepository.deleteById(id);
	}

	@Override
	public List<NoteDTO> findNoteByPage(Pageable pageable) {
      Page<NoteEntity> noteEntity = noteRepository.findAll(pageable);
      List<NoteDTO> noteDTO = objectMapped.mapAll(noteEntity.getContent(), NoteDTO.class);
		return noteDTO;
	}

}

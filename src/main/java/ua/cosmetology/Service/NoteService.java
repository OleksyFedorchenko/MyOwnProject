package ua.cosmetology.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import ua.cosmetology.DTO.NoteDTO;

public interface NoteService {
	
	void createNote(NoteDTO noteDTO);
	
	List<NoteDTO> findAll();
	
	NoteDTO findById(Long id);
	
	void deleteById(Long id, NoteDTO noteDTO);
	
	void updateNoteById(Long id, NoteDTO noteDTO);
	
	List<NoteDTO> findNoteByPage(Pageable pageable);

}

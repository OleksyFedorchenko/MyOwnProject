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
import org.springframework.web.bind.annotation.RestController;

import ua.cosmetology.DTO.ErrorDTO;
import ua.cosmetology.DTO.NoteDTO;
import ua.cosmetology.Service.NoteService;

@RestController
@RequestMapping("notes")
public class NoteController {
	@Autowired
	private NoteService noteService;

	@PostMapping
	public ResponseEntity<?> createNotes(@Valid @RequestBody NoteDTO noteDTO, BindingResult br) {

		if (br.hasErrors()) {

			String errMsg = br.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.findFirst().get().toString();
			ErrorDTO error = new ErrorDTO(errMsg);
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

		noteService.createNote(noteDTO);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<NoteDTO>> findAll(@PageableDefault Pageable pageable) {

		List<NoteDTO> noteDTO = noteService.findNoteByPage(pageable);

		return new ResponseEntity<List<NoteDTO>>(noteDTO, HttpStatus.OK);
	}

	@GetMapping("{noteId}")
	public ResponseEntity<NoteDTO> findById(@PathVariable("noteId") Long id) {

		NoteDTO noteDTO = noteService.findById(id);

		return new ResponseEntity<NoteDTO>(noteDTO, HttpStatus.OK);
	}

	@PostMapping("{noteId}")
	public ResponseEntity<?> updateNote(@Valid @PathVariable("noteId") Long id, NoteDTO noteDTO, BindingResult br) {

		if (br.hasErrors()) {

			String errMsg = br.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.findFirst().toString();
			ErrorDTO error = new ErrorDTO(errMsg);
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

		noteService.updateNoteById(id, noteDTO);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("{noteId}")
	public ResponseEntity<?> deleteNote(@PathVariable("noteId") Long id, NoteDTO noteDTO) {

		noteService.deleteById(id, noteDTO);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

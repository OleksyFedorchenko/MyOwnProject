package ua.cosmetology.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.cosmetology.Entity.NoteEntity;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

}

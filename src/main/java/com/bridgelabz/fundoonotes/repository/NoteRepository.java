package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.fundoonotes.model.Notes;

public interface NoteRepository extends CrudRepository<Notes, Long> {
	@Query(value=" select * from notes where note_id=?",nativeQuery = true)
	 Notes findNoteById(long noteId);
	@Query(value="select * from notes where info_user_id =?",nativeQuery = true)
	List<Notes> findNoteByUserId(int  id);
 

}

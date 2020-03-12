package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Notes;
@Repository
public interface NoteRepository extends CrudRepository<Notes, Long> {
	@Query(value=" select * from notes where note_id=?",nativeQuery = true)
	 Notes findNoteById(long noteId);
	@Query(value="select * from notes where user_id =?",nativeQuery = true)
	List<Notes> findNoteByUserId(int  id);
	@Query(value="select * from notes where is_trashed=1",nativeQuery=true)
	List<Notes> getTrashed(int id);
	@Query(value="select * from notes where is_pinned=1",nativeQuery=true)
	List<Notes> getPinned(int userId);
	@Query(value="select * from notes where is_archieved=1",nativeQuery=true)
	List<Notes> getArchieved(int userId);
 

}
 
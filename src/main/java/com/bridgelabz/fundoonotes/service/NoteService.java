package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.dto.NoteUpdate;
import com.bridgelabz.fundoonotes.model.Notes;

public interface NoteService {
	
	Notes createNote(NoteDto data,String token);
	
	Notes updateNote(NoteUpdate data,String token,long noteId);
	
	Notes deleteNote(long NoteId,String token);
	
	List<Notes> getAllNotes();
	
	List<Notes> getNoteByUserId(String token);
	
	Notes getNote(long noteId);
	
	List<Notes> getTrashed(String token);
	
	Notes pinned(String token, long noteId);
	
	List<Notes> getPinned(String token);
	
	List<Notes> getArchieved(String token);
	
	Notes archieved(String token, long noteId);
	
	Notes addColour(String token, long noteId,String colour);
	
}

    
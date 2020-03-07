package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.dto.NoteUpdate;
import com.bridgelabz.fundoonotes.model.Notes;

public interface NoteService {
	
	Notes createNote(NoteDto data,String token);
	Notes updateNote(NoteUpdate data,String token);
	Notes deleteNote(long NoteId,String token);
	List<Notes> getAllNotes();
	List<Notes> getNoteByUserId(String token);
	Notes getNote(long noteId);
	List<Notes> getTrashed(String token);
	
}

    
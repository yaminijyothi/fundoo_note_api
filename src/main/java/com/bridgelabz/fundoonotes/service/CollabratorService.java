package com.bridgelabz.fundoonotes.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoonotes.model.Notes;

public interface CollabratorService {

	Optional<Notes> addCollabrator(String email, String token, long noteId);

	List<Notes> getAllCollabrator(String token);
	
	Notes removeCollabrator(String email, String token, long noteId);
	

}

package com.bridgelabz.fundoonotes.implementation;
/*
 * author : yamini
 * 
 */
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.model.Notes;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.CollabratorService;
import com.bridgelabz.fundoonotes.utility.TokenGenerator;
@Service
public class CollabratorImpl implements CollabratorService{

	@Autowired
	private  UserRepository repository;
	@Autowired
	private NoteRepository noteRepo;
	@Autowired
	private TokenGenerator generator;
	//to add the collabrator
	@Override
	@Transactional
	public Optional<Notes> addCollabrator(String email, String token, long noteId) {
		int id= generator.jwt(token);
		UserInfo col=repository.getUser(email);
		if(col!=null) {

			Optional<Notes> note=noteRepo.findById(noteId);
			note.ifPresent(notes->{
				col.getCollabratorNote().add(notes);
				repository.register(col);
				
			});
			return note;

		}
		return null;				
 


	}
	//to get all collabrators
	@Override
	@Transactional
	public List<Notes> getAllCollabrator(String token) {
		int id= generator.jwt(token);
		UserInfo info=repository.findUserById(id);
		if(info!=null) {
			List<Notes> notes=info.getCollabratorNote();
			return notes;
		}
		return  null;

	}
	//to remove collabrator
	@Override
	@Transactional
	public Notes removeCollabrator(String email, String token, long noteId) {
		int id= generator.jwt(token);
		UserInfo info=repository.findUserById(id);
		if(info!=null) {
			UserInfo col=repository.getUser(email);
			if(col!=null) {
				Notes note=noteRepo.findNoteById(noteId);
				col.getCollabratorNote().remove(note);
				return note;
			}

		}

		return null;
	}
}





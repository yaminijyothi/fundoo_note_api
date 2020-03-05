package com.bridgelabz.fundoonotes.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.dto.NoteUpdate;
import com.bridgelabz.fundoonotes.model.Notes;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.NoteService;
import com.bridgelabz.fundoonotes.utility.TokenGenerator;
@Service
public class NoteImpl implements NoteService{
	@Autowired
	private NoteRepository noterepository;
	@Autowired
	private UserRepository repository;
	@Autowired
	private ModelMapper model;
	@Autowired
	private TokenGenerator generator;

	//to add notes
	@Override
	@Transactional
	public Notes createNote(NoteDto data, String token) {
		int id=generator.jwt(token);
		UserInfo info = repository.findUserById(id);
		if(info!=null) {
			Notes note=(Notes)model.map(data,Notes.class);
			note.setTitle(data.getTitle());
			note.setDescription(data.getDescription());
			note.setIsArchieved(0);
			note.setIsPinned(0);
			note.setColour("null");
			note.setReminder(null);
			note.setDateAndTime(LocalDateTime.now());
			note.setInfo(info);
			Notes result = noterepository.save(note);
			return result;
		}
		return null;
	}
	//to update notes
	@Override
	@Transactional
	public Notes updateNote(NoteUpdate data, String token) {
		int id=generator.jwt(token);
		UserInfo info = repository.findUserById(id);
		if(info!=null) {
			Notes note=model.map(data,Notes.class);
			note.setColour(data.getColour());
			note.setDescription(data.getDescription());
			note.setIsArchieved(data.getIsArchieved());
			note.setIsPinned(data.getIsArchieved());
			note.setIsTrashed(data.getIsTrashed());
			note.setTitle(data.getDescription());
			note.setUpdateDate(LocalDateTime.now());
			Notes result = noterepository.save(note);
			return result;
		}
		return null;
	}

	//to get all notes of all users
	@Override
	@Transactional
	public List<Notes> getAllNotes( ) {
		List<Notes> note=new ArrayList<Notes>();
		noterepository.findAll().forEach(note::add);
		return note;
	}

	//to delete notes 
	@Override
	public Notes deleteNote(long NoteId, String token) {
		int userId=generator.jwt(token);
		UserInfo info=repository.findUserById(userId);
		Notes note=noterepository.findNoteById(NoteId);
		if(note!=null) {
			note.setIsTrashed(1);
			Notes notes = noterepository.save(note);
			return notes;
		}
		return null;
	}

	//getting notes by userId
	@Override
	@Transactional
	public List<Notes> getNoteByUserId(String token) {
		int userId=generator.jwt(token);
		List<Notes> note=noterepository.findNoteByUserId(userId);
		if(note!=null) {
			return note;
		}
		return null;
	}

	//getting single notes
	@Override
	@Transactional
	public Notes getNote(long noteId) {
		Notes note=noterepository.findNoteById(noteId);
		return note;
	}
}

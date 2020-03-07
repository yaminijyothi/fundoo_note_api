package com.bridgelabz.fundoonotes.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.dto.NoteUpdate;
import com.bridgelabz.fundoonotes.exception.UserException;
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
	private TokenGenerator generator;

	//to add notes
	@Override
	@Transactional
	public Notes createNote(NoteDto data, String token) {
		int id=generator.jwt(token);
		try {
			UserInfo info = repository.findUserById(id);
			if(info!=null) {
				Notes note=new Notes();
				BeanUtils.copyProperties(data, note);
				note.setTitle(data.getTitle());
				note.setDescription(data.getDescription());
				note.setIsArchieved(0);
				note.setIsPinned(0);
				note.setColour("null");
				note.setReminder(null);
				note.setDateAndTime(LocalDateTime.now());
				info.getNotes().add(note);
				Notes result = noterepository.save(note);
				return result;
			}
		}catch(Exception e) {
			throw new UserException("user does not exist with that id");
		}
		return null;
	}
	//to update notes
	@Override
	@Transactional
	public Notes updateNote(NoteUpdate data, String token,long noteId) {
		int id=generator.jwt(token);
		try {
			UserInfo info = repository.findUserById(id);
			if(info!=null) {
				Notes note=noterepository.findNoteById(noteId);
				if(note!=null) {
					BeanUtils.copyProperties(data, note);
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
			}
		}catch(Exception e) {
			throw new UserException("user does not exist");
		}
		return null;
	}

	//to get all notes of all users
	@Override
	@Transactional
	public List<Notes> getAllNotes( ) {
		List<Notes> note=new ArrayList<>();
		noterepository.findAll().forEach(note::add);
		return note;
	}

	//to delete notes 
	@Override
	public Notes deleteNote(long NoteId, String token) {
		int userId=generator.jwt(token);
		UserInfo info = repository.findUserById(userId);
		if(info!=null) {
			Notes note=noterepository.findNoteById(NoteId);
			if(note!=null) {
				note.setIsTrashed(1);
				Notes notes = noterepository.save(note);
				return notes;
			}
		}
		return null;
	}

	//getting notes by userId
	@Override
	@Transactional
	public List<Notes> getNoteByUserId(String token) {
		int userId=generator.jwt(token);
		UserInfo info = repository.findUserById(userId);
		try {
			if(info!=null) {
				List<Notes> note=noterepository.findNoteByUserId(userId);
				return note;
			}
		}catch(Exception e) {
			throw new UserException("user does not exist");
		}
		return null;

	}

	//getting single notes
	@Override
	@Transactional
	public Notes getNote(long noteId) {
		try {
			Notes note=noterepository.findNoteById(noteId);
			return note;
		}catch(Exception e) {
			throw new UserException("no notes is there with that id");
		}
	}
	//getting trashed notes
	@Override
	@Transactional
	public List<Notes> getTrashed(String token) {
		int userId=generator.jwt(token);
		List<Notes> notes=noterepository.findNoteByUserId(userId);
		try {
			if(notes!=null) {
				List<Notes> list=noterepository.getTrashed(userId);
				return list;
			}
		}catch(Exception e) {
			throw new UserException("trashed notes does not exist");
		}
		return null;
	}
	//pinned to  notes
	@Override
	@Transactional
	public Notes pinned(String token, long noteId) {
		int userId=generator.jwt(token);
		UserInfo info = repository.findUserById(userId);
		try {
			if(info!=null) {
				Notes note=noterepository.findNoteById(noteId);
				if(note!=null) {
					note.setIsPinned(1);
					note=noterepository.save(note);
					return note;
				}
			}
		}catch(Exception e) {
			throw new UserException("user does not exist");
		}
		return null;
	}
	//getting pinned notes
	@Override
	@Transactional
	public List<Notes> getPinned(String token) {
		int userId=generator.jwt(token);
		UserInfo info = repository.findUserById(userId);
		try {
			if(info!=null) {
				List<Notes> notes=noterepository.findNoteByUserId(userId);
				if(notes!=null) {
					List<Notes> list=noterepository.getPinned(userId);
					return list;
				}	
			}
		}catch(Exception e) {
			throw new UserException("user does not exist");
		}
		return null;
	}
	//getting archieved notes
	@Override
	@Transactional
	public List<Notes> getArchieved(String token) {
		int userId=generator.jwt(token);
		UserInfo info = repository.findUserById(userId);
		try {
			if(info!=null) {
				List<Notes> notes=noterepository.findNoteByUserId(userId);
				if(notes!=null) {
					List<Notes> list=noterepository.getArchieved(userId);
					return list;
				}	
			}
		}catch(Exception e) {
			throw new UserException("user does nott exist");
		}
		return null;
	}
	//notes get archieved
	@Override
	@Transactional
	public Notes archieved(String token, long noteId) {
		int userId=generator.jwt(token);
		UserInfo info = repository.findUserById(userId);
		try {
			if(info!=null) {
				Notes note=noterepository.findNoteById(noteId);
				if(note!=null) {
					note.setIsArchieved(1);
					note=noterepository.save(note);
					return note;
				}
			}
		}catch(Exception e) {
			throw new UserException("user does not existed");
		}
		return null;
	}
	//add colour to notes
	@Override
	@Transactional
	public Notes addColour(String token, long noteId,String colour) {
		int userId=generator.jwt(token);
		UserInfo info = repository.findUserById(userId);
		try {
			if(info!=null) {
				Notes note=noterepository.findNoteById(noteId);
				if(note!=null) {
					note.setColour(colour);
					note=noterepository.save(note);
					return note;
				}
			}
		}catch(Exception e) {
			throw new UserException("user does not exist");
		}
		return null;
	}
}


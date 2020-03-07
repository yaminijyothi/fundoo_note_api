package com.bridgelabz.fundoonotes.implementation;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.LableDto;
import com.bridgelabz.fundoonotes.model.Lables;
import com.bridgelabz.fundoonotes.model.Notes;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.LableRepository;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.LableService;
import com.bridgelabz.fundoonotes.utility.TokenGenerator;
@Service
public class LableImpl implements LableService{
	@Autowired
	private TokenGenerator generator;
	@Autowired
	private UserRepository repository;
	@Autowired
	private LableRepository lableRepo;
	@Autowired
	private NoteRepository noteRepo;

	//to create lables
	@Override
	@Transactional
	public Lables createLable(LableDto data, String token) {
		int id=generator.jwt(token);
		UserInfo info=repository.findUserById(id);
		if(info!=null) {
			Lables lab=new Lables();
			//BeanUtils.copyProperties(data, lab);
			lab.setName(data.getName());
			lab.setUserId(id);
			Lables result = lableRepo.save(lab);
			return result;

		}
		return null;
	}

	//to update lables
	@Override
	@Transactional
	public Lables updateLable(LableDto data, String token,long lableId) {
		int id=generator.jwt(token);
		UserInfo info=repository.findUserById(id);
		if(info!=null) {
			Lables lab=new Lables();
			BeanUtils.copyProperties(token, lab);
			lab.setName(data.getName());
			Lables result = lableRepo.save(lab);
			return result;
		}
		return null;
	}


	//to delete lables
	@Override
	@Transactional
	public Lables deleteLable(long LableId, String token) {
		Lables lab=lableRepo.findLableById(LableId);
		if(lab!=null) {
			lableRepo.delete(lab);
			return lab;
		}

		return null;
	}


	//to get all lables of all users
	@Override
	@Transactional
	public List<Lables> getAllLables() {
		List<Lables> lables=new ArrayList<Lables>();
		lableRepo.findAll().forEach(lables::add);
		return lables;
	}


	//to get lables by userId
	@Override
	@Transactional
	public List<Lables> getLableByUserId(String token) {
		int id=generator.jwt(token);
		List<Lables> lab=lableRepo.findLableByUserId(id);
		return lab;
	}


	//to get a single lable
	@Override
	@Transactional
	public Lables getLable(long LableId) {
		Lables lab=lableRepo.findLableById(LableId);
		return lab;
	}
	/*
	 * // //// @Override //// public List<Lables> notesOfLable(LableDto data, String
	 * token) { //// int id=generator.jwt(token); //// UserInfo
	 * info=repository.findUserById(id); //// if(info!=null) { //// Lables
	 * lab=model.map(data, Lables.class); //// lab.setUserId(info.getUserId()); ////
	 * lableRepo.save(lab); //// List<Notes> note=noteRepo.findNoteByUserId(id);
	 * //// } //// return null; //// }
	 */
//	@Override
//	public Notes addNotesToLable(String token, long lableId, NoteDto data) {
//		int id=generator.jwt(token);
//		UserInfo info=repository.findUserById(id);
//		if(info!=null) {
//			List<Lables> lables=lableRepo.findLableByUserId(id);
//			if(lables!=null) {
//				Lables lab=lableRepo.findLableById(lableId);
//				if(lab!=null) {
//					Notes note=model.map(data,Notes.class);
//					note.setTitle(data.getTitle());
//					note.setDescription(data.getDescription());
//					note.setIsArchieved(0);
//					note.setIsPinned(0);
//					note.setIsTrashed(0);
//					note.setDateAndTime(LocalDateTime.now());
//					note.setColour(null);
//					note.setInfo(info);
//			       Notes result = noteRepo.save(note);
//					
//				  return result;
//				   
//				
//				}
//				
//				
//			}
//		}
//		return null;
//	}

	@Override
	public Lables addLable(long lableId, long noteId, String token) {
		int id=generator.jwt(token);
		UserInfo info=repository.findUserById(id);
		if(info!=null) {
			
			Notes notes =noteRepo.findNoteById(noteId);
			Lables lable=lableRepo.findLableById(lableId);
			lable.getNotesList().add(notes);
			lableRepo.save(lable); 
		return lable;
	}
		return null;
	}

	@Override
	public List<Notes> getNotes(String token, long lableId) {
		int id=generator.jwt(token);
		UserInfo info=repository.findUserById(id);
		if(info!=null) {
			Lables lable=lableRepo.findLableById(lableId);
			if(lable!=null) {
				List<Notes> notes=lableRepo.getAllNotes(lableId);
				return notes;
			}
		}
		return null;
	}
}

//	@Override
//	public List<Lables> getNotes(String token, Long lableId) {

//		if(info!=null) {
//			Lables lab=lableRepo.findLableById(lableId);
//			if(lab!=null) {
//				//List<Notes> notes=lableRepo.
//			}
//		}
//		return null;
//	}


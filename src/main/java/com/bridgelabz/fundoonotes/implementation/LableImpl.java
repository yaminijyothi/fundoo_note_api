package com.bridgelabz.fundoonotes.implementation;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.LableDto;
import com.bridgelabz.fundoonotes.exception.UserException;
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
		try {
			int id=generator.jwt(token);
			UserInfo info=repository.findUserById(id);
			if(info!=null) {
				Lables lab=new Lables();
				BeanUtils.copyProperties(data, lab);
				lab.setName(data.getName());
				lab.setUserId(id);
				Lables result = lableRepo.save(lab);
				return result;
			}
		}catch(Exception e) {
			throw new UserException("user does not exist with this id");
		}
		return null;
	}

	//to delete lables
	@Override
	@Transactional
	public Lables deleteLable(long LableId, String token) {
		try {
			Lables lab=lableRepo.findLableById(LableId);
			if(lab!=null) {
				lableRepo.delete(lab);
			}
		}catch(Exception e) {
			throw new UserException("lable does not exist with that id");
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

	//add  notes to lables
	@Override
	@Transactional
	public Lables addLable(long lableId, long noteId, String token) {

		int id=generator.jwt(token);
		UserInfo info=repository.findUserById(id);
		if(info!=null) {
			try {
				Notes notes =noteRepo.findNoteById(noteId);
				Lables lable=lableRepo.findLableById(lableId);
				lable.getNotesList().add(notes);
				lableRepo.save(lable); 
				return lable;
			}catch(Exception e) {
				throw new UserException("lable is not exist with that id");
			}
		}
		return null;
	}

	// getting notes by lable id
	@Override
	@Transactional
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

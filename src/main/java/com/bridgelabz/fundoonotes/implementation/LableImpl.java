package com.bridgelabz.fundoonotes.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
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
	@Autowired
	private Environment environment;

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
				info.getLables().add(lab);
				Lables result = lableRepo.save(lab);
				return result;
			}
		}catch(Exception e) {
			throw new UserException(environment.getProperty("400"),HttpStatus.BAD_REQUEST);
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
			throw new UserException(environment.getProperty("400"),HttpStatus.BAD_REQUEST);
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
		//lab.forEach(lab::add);
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
				notes.getLable().add(lable);
				lableRepo.save(lable); 
				return lable;
			}catch(Exception e) {
				throw new UserException(environment.getProperty("400"),HttpStatus.BAD_REQUEST);
			}
		}
		return null;
	}
	//lables sorting in ascending order
	@Override
	@Transactional
	public List<String> ascendingSort() {
		List<String> list=new ArrayList<>();
		List<Lables> lables=getAllLables();
		lables.forEach(data->{
			list.add(data.getName());
		});
		Collections.sort(list);
		return list;
	}
	//lables sorting in descending order
	@Override
	@Transactional
	public List<String> descendingSort() {

		List<String> list=new ArrayList<>();
		List<Lables> lables=getAllLables();
		lables.forEach(data->{
			list.add(data.getName());
		});
		Collections.reverse(list);
		return list;

	}
}

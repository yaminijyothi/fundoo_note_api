package com.bridgelabz.fundoonotes.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.LableDto;
import com.bridgelabz.fundoonotes.model.Lables;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.LableRepository;
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
	private  ModelMapper model;

	//to create lables
	@Override
	@Transactional
	public Lables createLable(LableDto data, String token) {
		int id=generator.jwt(token);
		UserInfo info=repository.findUserById(id);
		if(info!=null) {
			Lables lab=model.map(data, Lables.class);
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
	public Lables updateLable(LableDto data, String token) {
		int id=generator.jwt(token);
		UserInfo info=repository.findUserById(id);
		if(info!=null) {
			Lables lab=model.map(data, Lables.class);
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

}

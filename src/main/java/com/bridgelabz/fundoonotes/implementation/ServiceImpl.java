package com.bridgelabz.fundoonotes.implementation;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.RegDto;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utility.Mail;
import com.bridgelabz.fundoonotes.utility.TokenGenerator;



@Service
public class ServiceImpl implements UserService{

	@Autowired
    private UserRepository repository;
	@Autowired
	private ModelMapper model;
	@Autowired
    private BCryptPasswordEncoder encrypt;
    @Autowired
	private TokenGenerator generator;
    @Autowired
	 private Mail mail;
    
	@Transactional
	public boolean register(RegDto data) {
		UserInfo info = repository.getUser(data.getEmail());
		if(info==null) {
			info=model.map(data,UserInfo.class);
			 info.setDate(LocalDateTime.now());
     		 String enpassword=encrypt.encode(data.getPassword());
			 info.setPassword(enpassword);
   		     info.setIsverified(true);
		     repository.register(info);	
		     
		  }
		return true;
	}
	
	@Override
	@Transactional
	public UserInfo login(LoginDto data) {
		UserInfo info=repository.getUser(data.getEmail());
		if(info!=null) {
			if((info.getIsverified()==true)&&(encrypt.matches(data.getPassword(), info.getPassword()))) {
				System.out.println(generator.token(info.getUserId()));
			}
		}
		return info;
	}

	@Override
	@Transactional
	public boolean verify(String token) {
		long userId=(Long)generator.jwt(token);
		repository.verify(userId);
		return true;
	}

	@Override
	public List<UserInfo> users() {
		List<UserInfo> users=repository.users();
		UserInfo user=users.get(0);
		return users;
	}

	

	@Override
	public UserInfo getUser(String token) {
		long userId=generator.jwt(token);
		UserInfo info=repository.findUserById(userId);
		return info;
	}

	@Override
	public boolean isUser(String email) {
		UserInfo info=repository.getUser(email);
		if(info.getIsverified()) {
			
		}
		return false;
	}
}
	
	

	



package com.bridgelabz.fundoonotes.implementation;
/*
 * service implemetation for login and registration
 */
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.configuration.UserConfiguration;
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
	private JavaMailSenderImpl sender;
	@Autowired
	private UserConfiguration config;


	//for user registration
	@Transactional
	public UserInfo register(RegDto data) {
		UserInfo info = repository.getUser(data.getEmail());
		if(info == null) {
			UserInfo inform=(UserInfo)model.map(data,UserInfo.class);
			inform.setDate(LocalDateTime.now());
			String enpassword=encrypt.encode(data.getPassword());    
			inform.setPassword(enpassword);
			inform.setIsverified(false);
			UserInfo result = repository.register(inform);
			JavaMailSenderImpl mail = this.mailSender();
			Mail.sending(inform,mail, generator.token(inform.getUserId()));
			System.out.println(generator.token(inform.getUserId()));
			return result; 

		}
		return null;
	}
	//for login
	@Override
	@Transactional
	public UserInfo login(LoginDto data) {
		UserInfo info=repository.getUser(data.getEmail());
		if(info!=null) {
			if((info.getIsverified()==true)&&(encrypt.matches(data.getPassword(), info.getPassword()))) {
				System.out.println(generator.token(info.getUserId()));
				return info;
			}
		}
		return null;
	}
	
	//to verify user by using token
	@Override
	@Transactional
	public boolean verify(String token) {
		int userId=generator.jwt(token);
		repository.verify(userId);
		return true;
	}
	//to reset the password(forgotpassword)
	@Override
	@Transactional
	public UserInfo forgotPassword(LoginDto data) {
		UserInfo info=repository.getUser(data.getEmail());
		if(info!=null) {
			UserInfo inform=model.map(data, UserInfo.class);
			inform.setPassword(config.passwordEncoder().encode(inform.getPassword()));
			return repository.register(inform);

		}
		return null;
	}
	//to get all users
	@Override
	public List<UserInfo> users() {
		List<UserInfo> users=repository.users(); 
		UserInfo user=users.get(0); 
		 return users;
	
	}
	

   //**propertyfile to send mail 
	public JavaMailSenderImpl mailSender() {
		sender.setUsername(System.getenv("email"));
		sender.setPassword(System.getenv("password"));	
		sender.setPort(587);
		Properties p=new Properties();
		p.put("mail.smtp.auth","true");
		p.put("mail.smtp.starttls.enable","true");
		p.put("mail.smtp.host","smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		sender.setJavaMailProperties(p);
		return sender;

	}
		
}
	







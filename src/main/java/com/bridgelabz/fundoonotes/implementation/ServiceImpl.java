package com.bridgelabz.fundoonotes.implementation;
/*
 * service implemetation for login and registration
 */
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.configuration.UserConfiguration;
import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.RegDto;
import com.bridgelabz.fundoonotes.exception.UserException;
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
	private BCryptPasswordEncoder encrypt;
	@Autowired
	private TokenGenerator generator;
	@Autowired
	private JavaMailSenderImpl sender;
	@Autowired
	private UserConfiguration config;
	@Autowired
	private Environment environment;


	//for user registration
	@Transactional
	public UserInfo register(RegDto data) {
		UserInfo user = repository.getUser(data.getEmail());
		if(user==null) {
		UserInfo info=new UserInfo();
			BeanUtils.copyProperties(data,info);
			info.setDate(LocalDateTime.now());
			String enpassword=encrypt.encode(data.getPassword());    
			info.setPassword(enpassword);
			info.setIsverified(false);
			UserInfo result = repository.register(info);
			JavaMailSenderImpl mail = this.mailSender();
			Mail.sending(info,mail, generator.token(info.getUserId()));
			System.out.println(generator.token(info.getUserId()));
			return info;
		}
		return null;
	}
	//for login
	@Override
	@Transactional
	public UserInfo login(LoginDto data) {
		try {
		UserInfo info=repository.getUser(data.getEmail());
		if(info!=null) {
			if((info.isIsverified()==true)&&(encrypt.matches(data.getPassword(), info.getPassword()))) {
				System.out.println(generator.token(info.getUserId()));
				return info;
			}
		}
		}catch(Exception e) {
			throw new UserException(environment.getProperty("400"),HttpStatus.BAD_REQUEST);
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
		try {
		UserInfo info=repository.getUser(data.getEmail());
		if(info!=null) {
			BeanUtils.copyProperties(data, info);
			info.setPassword(config.passwordEncoder().encode(info.getPassword()));
			return repository.register(info);
        }
		}catch(Exception e) {
			throw new UserException(environment.getProperty("400"),HttpStatus.BAD_REQUEST);
		}
		return null;
	}
	//to get all users
	@Override
	public List<UserInfo> users() {
		List<UserInfo> users=repository.users(); 
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
	//getting user by id
	@Override
	@Transactional
	public UserInfo getUserById(String token) {
		int userId=generator.jwt(token);
		UserInfo info=repository.findUserById(userId);
		if(info!=null) {
			return info;
		}
		return null;
	}
	
		
}
	







package com.bridgelabz.fundoonotes.controller;
/*
 * purpose:implementing rest controller for login,registration and forgot password
 * Filename:UserController.java
 * author:yamini
 * */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.ForgotDto;
import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.RegDto;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.response.StatusRes;
import com.bridgelabz.fundoonotes.response.UserDetailResponse;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utility.TokenGenerator;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	@Autowired                             
	private TokenGenerator generator;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               

	// API for register
	@PostMapping("/register")
	public ResponseEntity<StatusRes> register(@RequestBody RegDto data) {
		UserInfo info = service.register(data);
		if (info!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("successfully registered", 200, info));
		}
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new StatusRes("user already existed", 400));
}

	// API for login
	@PostMapping("/login")
	public ResponseEntity<UserDetailResponse> login(@RequestBody LoginDto data) {
		UserInfo user = service.login(data);
		if (user != null) {
		   String token = generator.token(user.getUserId());
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new UserDetailResponse(token,"login successfully 200", user));
		}
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserDetailResponse(null,"failed to login 400",data));
	}

	//API for verify token                           
	@GetMapping("/verify/{token}")
	public ResponseEntity<StatusRes> verify(@PathVariable("token") String token){
		boolean ver=service.verify(token);
		if(ver) 
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("verified",200,ver));
		}
		return	ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusRes("not verified",400)); 
   }


	// API for getting all users
	@GetMapping("/allusers")
	public ResponseEntity<StatusRes> Users(){
		List<UserInfo> user=service.users();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("all users information ",200,user));

	}

	//API for forgotpassword
	@PostMapping("/forgotpassword") 
	public ResponseEntity<StatusRes> forgetPassword(@RequestBody ForgotDto data){
		UserInfo info=service.forgotPassword(data);
		if(info!=null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("password updated",200,data));
		}
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new StatusRes("password is not updated", 400,data));

	}
	//API for verify token 
		@GetMapping("/getUserById/{token}")
		public ResponseEntity<StatusRes>getUserById(@PathVariable("token") String token){
			UserInfo info=service.getUserById(token);
			if(info!=null) 
			{
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("verified",200,info));
			}
			return	ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusRes("not verified",404)); 
		}
//		@GetMapping("/hi")
//		public String hello() {
//			return "hi";
//		}

		
	}



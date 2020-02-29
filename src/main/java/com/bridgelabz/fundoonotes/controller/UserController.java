package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.RegDto;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.response.StatusRes;
import com.bridgelabz.fundoonotes.response.UserDetailResponse;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utility.TokenGenerator;

import io.swagger.annotations.ResponseHeader;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private TokenGenerator generator;

	// API for register
	@PostMapping("/user/register")
	public ResponseEntity<StatusRes> register(@RequestBody RegDto data) {
		boolean flag = service.register(data);
		if (flag) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new StatusRes("successfully registered", 200, data));
		}
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new StatusRes("registration failed", 400, data));

	}

	// API for login
	@PostMapping(value = "/user/login")
	public ResponseEntity<UserDetailResponse> login(@RequestBody LoginDto data) {
		UserInfo user = service.login(data);
		if (user != null) {
			String token = generator.token(user.getUserId());
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new UserDetailResponse("login successfully 200", data));
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserDetailResponse("failed to login 400", data));

	}

	//API for verify token 
    @PostMapping(value="/verify/{token}")
	public ResponseEntity<StatusRes> verify(@PathVariable("token") String token){
		boolean ver=service.verify(token);
		if(ver) 
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("verified",200));
			}
		return	ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("not verified",400)); 
	}
    
    
   // API for getting all users
    @GetMapping("/user/users")
    public ResponseEntity<StatusRes> Users(){
    	List<UserInfo> user=service.users();
	 return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("all users information ",200,user));
	  
	  }

	//API for forgotpassword
	  
	  @PostMapping(value="/user/pass") 
	  public ResponseEntity<StatusRes> forgetPassword(@RequestParam("email") String email){
	   boolean res=service.isUser(email);
	  if(res)
	  {
	     return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("user existed",200,email)); 
	     } 
	     return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusRes("user is not exist",400,email));
	      }
	  
    
    
	// API for getting single user 
    @GetMapping("user/singleuser")
	 public ResponseEntity<StatusRes>  getUser(@RequestHeader("token") String token)
	  {
	    UserInfo user=service.getUser(token); 
	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new  StatusRes("wanted user",200,user));
	  }
	 
	  



}

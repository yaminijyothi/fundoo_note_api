package com.bridgelabz.fundoonotes.service;

//service 
import java.util.List;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.RegDto;
import com.bridgelabz.fundoonotes.model.UserInfo;


public interface UserService {

	public  UserInfo  register(RegDto  data);

	public UserInfo login(LoginDto data);

	UserInfo forgotPassword(LoginDto data);

	public boolean verify(String token);

	List<UserInfo> users();

}

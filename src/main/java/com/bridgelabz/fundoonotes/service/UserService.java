package com.bridgelabz.fundoonotes.service;


import java.util.List;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.RegDto;
import com.bridgelabz.fundoonotes.model.UserInfo;


public interface UserService {
	
   public  boolean  register(RegDto  data);
   
   public UserInfo login(LoginDto data);

   public boolean verify(String token);
   
  List<UserInfo> users();
   
   boolean isUser(String email);

   public UserInfo getUser(String token);
   
   
}

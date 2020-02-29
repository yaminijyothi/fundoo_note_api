package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.RegDto;
import com.bridgelabz.fundoonotes.model.UserInfo;

public interface UserRepository {

	UserInfo  register(UserInfo  info);

	UserInfo findUserById(long id);

	UserInfo getUser(String email);

	List<UserInfo> users();

	boolean verify(long userId);


}

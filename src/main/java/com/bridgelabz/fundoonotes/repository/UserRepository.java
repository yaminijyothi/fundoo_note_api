package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import com.bridgelabz.fundoonotes.model.UserInfo;
//service repository
public interface UserRepository {

	UserInfo  register(UserInfo  info);

	UserInfo getUser(String email);

	List<UserInfo> users();

	boolean verify(int userId);


}

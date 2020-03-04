package com.bridgelabz.fundoonotes.model;
//main entity dto class for user details
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  int userId;
	private String name;
	private String email;
	private String password;
	private String mobile;
	private boolean isverified;
	private LocalDateTime date;
	
	/*
	 * @OneToMany(mappedBy ="info") private List<Notes> notes=new ArrayList<>();
	 */
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public boolean getIsverified() {
		return isverified;
	}
	public void setIsverified(boolean isverified) {
		this.isverified = isverified;
	}
	

}

package com.bridgelabz.fundoonotes.model;
//main entity dto class for user details
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
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

}

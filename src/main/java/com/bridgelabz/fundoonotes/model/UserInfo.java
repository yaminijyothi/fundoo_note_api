package com.bridgelabz.fundoonotes.model;
//main entity dto class for user details
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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

	@ManyToMany
	private List<Notes> collabrator;
	
	@OneToMany(cascade = CascadeType.ALL,targetEntity = Notes.class)
	@JoinColumn(name="userId")
	private List<Notes> notes;
	
	
	
	
}

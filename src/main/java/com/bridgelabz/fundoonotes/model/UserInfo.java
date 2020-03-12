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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="UserInfo")
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
	private List<Notes> collabratorNote;

	@OneToMany(targetEntity = Notes.class,cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	private List<Notes> notes;

	@OneToMany(targetEntity = Lables.class,cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	private List<Lables> lables;
}

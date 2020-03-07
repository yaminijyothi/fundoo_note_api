package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Notes {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long noteId;
	private String title;
	private String description;
	private int isArchieved;
	private int isPinned;
	private int isTrashed;
	private String colour;
	private LocalDateTime dateAndTime;
	private LocalDateTime updateDate;
	private	LocalDateTime reminder;
//	
//	@ManyToOne
//	private UserInfo info;
//	
//	@ManyToMany(mappedBy="notesList")
//	private List<Lables> lable;
//	
//	
	
	}


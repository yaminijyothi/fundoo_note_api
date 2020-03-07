package com.bridgelabz.fundoonotes.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Lables {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long lableId;
	private String name;
	private int userId;
	
	@ManyToMany()
	private List<Notes> notesList;
	
}

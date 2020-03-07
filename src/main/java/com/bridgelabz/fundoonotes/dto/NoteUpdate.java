package com.bridgelabz.fundoonotes.dto;

import lombok.Data;
@Data
public class NoteUpdate {
	private String title;
	private String description;
	private int isArchieved;
	private int isPinned;
	private int isTrashed;
	private String colour;
	}

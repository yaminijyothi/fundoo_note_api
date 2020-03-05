package com.bridgelabz.fundoonotes.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class NoteUpdate {
	private String title;
	private String description;
	private int isArchieved;
	private int isPinned;
	private int isTrashed;
	private String colour;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsArchieved() {
		return isArchieved;
	}
	public void setIsArchieved(int isArchieved) {
		this.isArchieved = isArchieved;
	}
	public int getIsPinned() {
		return isPinned;
	}
	public void setIsPinned(int isPinned) {
		this.isPinned = isPinned;
	}
	public int getIsTrashed() {
		return isTrashed;
	}
	public void setIsTrashed(int isTrashed) {
		this.isTrashed = isTrashed;
	}

	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	}

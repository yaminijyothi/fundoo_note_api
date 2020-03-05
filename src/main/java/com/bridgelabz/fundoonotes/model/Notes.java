package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
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
	
	@ManyToOne
	private UserInfo info;
	
	@ManyToMany(targetEntity = Lables.class)
	private List<Lables> lable;
	
	
	public long getNoteId() {
		return noteId;
	}
	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}
	public int getInfo() {
		return info.getUserId();
	}
	public void setInfo(UserInfo info) {
		this.info = info;
	}
	public List<Lables> getLable() {
		return lable;
	}
	public void setLable(List<Lables> lable) {
		this.lable = lable;
	}
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
	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	public LocalDateTime getReminder() {
		return reminder;
	}
	public void setReminder(LocalDateTime reminder) {
		this.reminder = reminder;
	}
}

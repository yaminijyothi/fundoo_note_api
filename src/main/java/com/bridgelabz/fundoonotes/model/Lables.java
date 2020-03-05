package com.bridgelabz.fundoonotes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Lables {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long lableId;
	private String name;
	private int userId;

	public long getLableId() {
		return lableId;
	}

	public void setLableId(long lableId) {
		this.lableId = lableId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}

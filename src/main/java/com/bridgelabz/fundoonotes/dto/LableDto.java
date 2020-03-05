package com.bridgelabz.fundoonotes.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class LableDto {
private String name;


public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
}

package com.bridgelabz.fundoonotes.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

//exception handling
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ToString
public class UserException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String StatusMsg;
	private HttpStatus status;
	public UserException(String statusMsg, HttpStatus status) {
		super();
		StatusMsg = statusMsg;
		this.status = status;
	}
	
	
}

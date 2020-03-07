package com.bridgelabz.fundoonotes.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

//exception handling
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private int StatusCode;
	private String StatusMsg;
	public UserException(String Statusmsg) {
		super(Statusmsg);
	}


}

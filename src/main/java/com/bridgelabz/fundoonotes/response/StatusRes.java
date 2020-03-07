package com.bridgelabz.fundoonotes.response;
//http status response class
import lombok.Data;

@Data
public class StatusRes {
	private String msg;
	private int statuscode;
	private Object obj;
	
	
	
	public StatusRes(String msg, int scode, Object obj) {
		super();
		this.msg = msg;
		this.statuscode = scode;
		this.obj = obj;
	}
	public StatusRes(String msg, int scode) {
		super();
		this.msg = msg;
		this.statuscode = scode;
	}
}

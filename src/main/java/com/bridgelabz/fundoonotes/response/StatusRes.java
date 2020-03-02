package com.bridgelabz.fundoonotes.response;
//http status response class
import lombok.Data;

@Data
public class StatusRes {
	private String msg;
	private int statuscode;
	private Object obj;
	
	
	
	public StatusRes(String msg, int scode, Object res) {
		super();
		this.msg = msg;
		this.statuscode = scode;
		this.obj = res;
	}
	public StatusRes(String msg, int scode) {
		super();
		this.msg = msg;
		this.statuscode = scode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getScode() {
		return statuscode;
	}
	public void setScode(int scode) {
		this.statuscode = scode;
	}
	public Object getRes() {
		return obj;
	}
	public void setRes(Object res) {
		this.obj = res;
	}

}

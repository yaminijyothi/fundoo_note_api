package com.bridgelabz.fundoonotes.response;

import lombok.Data;

@Data
public class StatusRes {
	private String msg;
	private int scode;
	private Object res;
	
	
	
	public StatusRes(String msg, int scode, Object res) {
		super();
		this.msg = msg;
		this.scode = scode;
		this.res = res;
	}
	public StatusRes(String msg, int scode) {
		super();
		this.msg = msg;
		this.scode = scode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getScode() {
		return scode;
	}
	public void setScode(int scode) {
		this.scode = scode;
	}
	public Object getRes() {
		return res;
	}
	public void setRes(Object res) {
		this.res = res;
	}

}

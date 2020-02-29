package com.bridgelabz.fundoonotes.response;

import lombok.Data;
@Data
public class UserDetailResponse {
 private String token;
 private String msg;
 private Object data;
 
 public  UserDetailResponse(String msg,Object data) {
	// this.token=token;
	 this.msg=msg;
	 this.data=data;
 }
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
public String  getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public Object getRes() {
	return data;
}
public void setRes(Object data) {
	this.data =data;
}
}
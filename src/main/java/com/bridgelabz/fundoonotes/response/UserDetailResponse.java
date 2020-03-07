package com.bridgelabz.fundoonotes.response;
//http response for userdetails
import lombok.Data;
@Data
public class UserDetailResponse {
 private String token;
 private String msg;
 private Object data;
 
 public  UserDetailResponse(String token,String msg,Object data) {
	this.token=token;
	 this.msg=msg;
	 this.data=data;
}
} 
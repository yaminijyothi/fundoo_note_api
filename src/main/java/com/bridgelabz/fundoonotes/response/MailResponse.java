package com.bridgelabz.fundoonotes.response;

import org.springframework.stereotype.Component;
//mailresponse to send mail in url form
@Component
public class MailResponse {
 public String message(String url,String token) {
	 return url+"/"+token;
 }
}

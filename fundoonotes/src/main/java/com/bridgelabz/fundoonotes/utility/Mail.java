package com.bridgelabz.fundoonotes.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class Mail {
	
 @Autowired
 JavaMailSender sender;
 public  void sending(String email,String response) {
	 SimpleMailMessage msg=new SimpleMailMessage();
	 msg.setTo(email);
	 msg.setSubject("mail is verified");
	 msg.setText(response);
	 sender.send(msg);
	 
 }
}

package com.bridgelabz.fundoonotes.utility;
//sending mail object
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.model.UserInfo;
@Component
public class Mail {
	
 public static void sending(UserInfo info, JavaMailSenderImpl sender,String token) {
	 
	 SimpleMailMessage msg=new SimpleMailMessage();
	 msg.setTo(info.getEmail());
	 msg.setSubject("regitration confirmed");
	 msg.setText("hi "+info.getName()+"  link to verify the user:\n"+"http://localhost:8083/verify/"+token);
	 sender.send(msg);
	 
 }
}

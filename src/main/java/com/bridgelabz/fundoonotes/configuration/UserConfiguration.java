package com.bridgelabz.fundoonotes.configuration;
/*user configuration for encryptpassword,modelmapper,javamailsender*/
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration {
	
	@Bean
	public  BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	    
	}
	@Bean                                   
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public JavaMailSenderImpl sender() {
		return new JavaMailSenderImpl();
	}

	
}

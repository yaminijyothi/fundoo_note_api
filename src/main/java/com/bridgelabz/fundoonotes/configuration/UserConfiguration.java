package com.bridgelabz.fundoonotes.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserConfiguration {
	
	@Bean
	public  BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	    
	}
	@Bean
	public JavaMailSenderImpl sender() {
		return new JavaMailSenderImpl();
	}

	
}

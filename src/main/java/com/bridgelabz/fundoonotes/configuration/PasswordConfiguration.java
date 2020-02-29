package com.bridgelabz.fundoonotes.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfiguration {
	
	@Bean
	public  BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	    
	}
	@Bean                                   
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	
}

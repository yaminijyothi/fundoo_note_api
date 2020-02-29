package com.bridgelabz.fundoonotes.utility;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
@Component
public class TokenGenerator {
	
	private static final String SECRET="929099573";
 public String token(long userId) {
	 String token=null;
	 try {
	 token=JWT.create().withClaim("id",userId).sign(Algorithm.HMAC384(SECRET));
	 }catch(IllegalArgumentException | JWTCreationException e) {
		 e.printStackTrace();
	 }
	 return token;
	 
 }
public Long jwt(String jwt) {
	Long userId=(long)0;
	if(jwt!=null) {
		userId=JWT.require(Algorithm.HMAC384(SECRET)).build().verify(jwt).getClaim("id").asLong();
    }
return userId;
}

}

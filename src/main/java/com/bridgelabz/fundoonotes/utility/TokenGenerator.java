package com.bridgelabz.fundoonotes.utility;
//generate the token using jwt token generator to confirm registration
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
	public int jwt(String jwt) {
		int userId=0;
		if(jwt!=null) {
			userId=JWT.require(Algorithm.HMAC384(SECRET)).build().verify(jwt).getClaim("id").asInt();
		}
		return userId;
	}
}

package com.wmanager.auth.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wmanager.auth.model.UserModel;

@Service
public class TokenService {

	private String secret = "segredinho";

	public String generateToken(UserModel user) {
		 try {
			 Algorithm algorithm =  Algorithm.HMAC256(secret);
			 return JWT.create().withIssuer("auth-api")
					 .withSubject(user.getEmail())
	                    .withExpiresAt(generateExpirationDate())
	                    .sign(algorithm);
			 
		 }catch (JWTCreationException e) {
			 throw new RuntimeException("Error to generate token " + e.getMessage());
		}
	}
	
	public String validateToken(String token) {
		try {
			 Algorithm algorithm =  Algorithm.HMAC256(secret);
			 return JWT.require(algorithm)
	                    .withIssuer("auth-api")
	                    .build()
	                    .verify(token)
	                    .getSubject();
			 
		 }catch (JWTVerificationException e) {
			 throw new RuntimeException("Error to validate token " + e.getMessage());
		}
	}

	
	//Faz um calc para add 2 horas a mais da hora da geração do token
	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}

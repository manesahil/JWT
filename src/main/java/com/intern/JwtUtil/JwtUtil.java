package com.intern.JwtUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private String SecretKey = "fjvhcbjadsvhcfjofoahdsvcadvhfoHSVdfvasdfXgrcdafR22456465243564ACSFAJBKOSXF";
	private long ExxpirationTime = 1000 * 60 * 60;
 
	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}
 
	public String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + ExxpirationTime))
				.signWith(SignatureAlgorithm.HS256, SecretKey).compact();
	}
 
	public boolean validateToken(String token, String username) {
		final String extractedUsername = extractUsername(token);
		return (extractedUsername.equals(username) && !isTokenExpired(token));
 
	}
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
 
	public Date extractExpiration(String token) {
		return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().getExpiration();
	}
 
	public String extractUsername(String token) {
		return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().getSubject();
	}
	
	
}

package com.spring_security_exmp.Address_Person.config;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spring_security_exmp.Address_Person.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	
	
	private String SECRET_KEY = "ships";
	
	
	public String generateToken(UserDetails userDetails, User user) {
		return doGenerateToken(userDetails, user, 1000 * 60);
	}

	private String doGenerateToken(UserDetails userDetails, User user, int duration) {
		Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
		claims.put("scopes", userDetails.getAuthorities());
		claims.put("user_name", userDetails.getUsername());
		if(user.getUserName() != null) {
			claims.put("details", user.getUserName());
		}
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + duration))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) throws ExpiredJwtException {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}

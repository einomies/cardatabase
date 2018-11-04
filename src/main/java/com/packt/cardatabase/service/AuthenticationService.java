package com.packt.cardatabase.service;

import static java.util.Collections.emptyList;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationService {

	static final long EXPIRATIONTIME = 86400000; // 1 day in milliseconds

//	algorithm-specific signing key used to digitally sign the JWT
//	You should use a base64 encoded string
	static final String SIGNINGKEY = "87qyfasdkfksahdfk";

//	defines the prefix of the token, the Bearer schema is typically used
	static final String PREFIX = "Bearer";

//	addToken method creates the token and adds it to the request's Authorization header
//	method also adds Access-Control-Expose-Headers to the header with the Authorization value
	static public void addToken(HttpServletResponse res, String username) {
		String JwtToken = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SIGNINGKEY).compact();
		res.addHeader("Authorization", PREFIX + " " + JwtToken);
		res.addHeader("Access-Control-Expose-Headers", "Authorization");
	}

	// Get token from Authorization header
	static public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null) {
			String user = Jwts.parser().setSigningKey(SIGNINGKEY).parseClaimsJws(token.replace(PREFIX, "")).getBody()
					.getSubject();
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, emptyList());
			}
		}
		return null;
	}

}

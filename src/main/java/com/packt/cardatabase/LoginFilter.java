package com.packt.cardatabase;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packt.cardatabase.domain.AccountCredentials;
import com.packt.cardatabase.service.AuthenticationService;

/*
 * This class handles POST requests to the /login endpoint. The LoginFilter class extends the Spring Security
 * AbstractAuthenticationProcessingFilter, which requires that you set the authenticationManager property. 
 * Authentication is performed by the attemptAuthentication method. If the authentication is successful, the 
 * succesfulAuthentication method is executed. This method will then call the addToken method in our service class 
 * and the token will be added to the Authorization header.
 */

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	public LoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		AccountCredentials creds = new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
				creds.getPassword(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		AuthenticationService.addToken(res, auth.getName());
	}
}
